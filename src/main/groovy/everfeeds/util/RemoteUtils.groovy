@Typed package everfeeds.util

import everfeeds.dao.CategoryDAO
import everfeeds.dao.TagDAO
import everfeeds.mongo.AccessD
import everfeeds.mongo.CategoryD
import everfeeds.mongo.TagD
import everfeeds.remote.Remote
import everfeeds.mongo.FilterD
import everfeeds.mongo.EntryD

/**
 * @author Dmitry Kurinskiy
 * @since 02.07.11 13:22
 */
class RemoteUtils {
   static final public boolean filterAfterParse(FilterD filterD, EntryD entry) {
    // Perform after-parse filtering
        if (filterD.withTags.size() && !entry.tags.intersect(filterD.withTags).size()) {
          return false;
        }
        if (filterD.withoutTags.size() && entry.tags.intersect(filterD.withoutTags).size()) {
          return false;
        }
        if (filterD.kinds.size()) {
          if (filterD.kindsWith && !filterD.kinds.contains(entry.kind)) return false;
          else if (filterD.kinds.contains(entry.kind)) return false;
        }
    true
  }

    static final public Map<String,TagD> getTagsCache(AccessD access, Remote remote){
    // Prepare tags cache
    Map<String, TagD> tags = [:]
    remote.getActualizedTags(access).each{
      tags.put it.identity, it
    }
    tags
  }

  static public List<TagD> actualizeTags(AccessD access, List remoteObjs, Closure objIdentity, Closure objTitle, Closure objParentIdentity = {null}) {
    Map<String, TagD> tagDMap = [:]
    Map<String, String> parentIds = [:]

    // Cache identity->CategoryD map; remove tags deleted remotely
    TagDAO.instance.findAllByAccess(access).each {TagD td ->
      remoteObjs.any { objIdentity.call(it) == td.identity} ? tagDMap.put(td.identity, td) : TagDAO.instance.delete(td)
    }
    // Sync categories, except parents (no access for notebooks grouping in current api version?)
    remoteObjs.each {
      final String identity = objIdentity.call(it)
      TagD tagD = tagDMap.containsKey(identity) ? tagDMap[identity] : new TagD(identity: identity, access: access)
      tagD.title = objTitle.call(it)
      TagDAO.instance.save(tagD)
      if (!tagDMap.containsKey(tagD.identity)) {
        tagDMap.put(tagD.identity, tagD)
      }
      parentIds.put(identity, objParentIdentity.call(it) as String)
    }
    parentIds.keySet().each {
      TagD prnt = parentIds[it] ? tagDMap[parentIds.get(it)] : null
      if (tagDMap[it].parent?.id == prnt?.id) return;
      tagDMap[it].parent = prnt
      TagDAO.instance.save(tagDMap[it])
    }

    // Finished!
    tagDMap.values().asList()
  }

  static public List<CategoryD> actualizeCategories(AccessD access, List remoteObjs, Closure objIdentity, Closure objTitle, Closure objParentIdentity = {null}) {
    Map<String, CategoryD> categoryDMap = [:]
    Map<String, String> parentIds = [:]

    // Cache identity->CategoryD map; remove tags deleted remotely
    CategoryDAO.instance.findAllByAccess(access).each {CategoryD cd ->
      remoteObjs.any { objIdentity.call(it) == cd.identity} ? categoryDMap.put(cd.identity, cd) : CategoryDAO.instance.delete(cd)
    }
    // Sync categories, except parents (no access for notebooks grouping in current api version?)
    remoteObjs.each {
      final String identity = objIdentity.call(it)
      CategoryD categoryD = categoryDMap.containsKey(identity) ? categoryDMap[identity] : new CategoryD(identity: identity, access: access)
      categoryD.title = objTitle.call(it)
      CategoryDAO.instance.save(categoryD)
      if (!categoryDMap.containsKey(categoryD.identity)) {
        categoryDMap.put(categoryD.identity, categoryD)
      }
      parentIds.put(identity, objParentIdentity.call(it) as String)
    }
    parentIds.keySet().each {
      CategoryD prnt = parentIds[it] ? categoryDMap[parentIds.get(it)] : null
      if (categoryDMap[it].parent?.id == prnt?.id) return;
      categoryDMap[it].parent = prnt
      CategoryDAO.instance.save(categoryDMap[it])
    }

    // Finished!
    categoryDMap.values().asList()
  }

}
