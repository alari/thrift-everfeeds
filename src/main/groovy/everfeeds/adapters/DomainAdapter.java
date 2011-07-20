package everfeeds.adapters;

import everfeeds.dao.EntryDAO;
import everfeeds.mongo.EntryD;
import everfeeds.mongo.FilterD;
import everfeeds.thrift.domain.Entry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Boris G. Tsirkin<mail@dotbg.name>
 * @since 15.07.2011
 */
public class DomainAdapter {
  public static List<Entry> domainsToThrift (List<EntryD> entryDList) {
    List<Entry> entries = new ArrayList<Entry>();
    for (EntryD eD : entryDList) {
      Entry e = new Entry();
      eD.syncToThrift(e);
      entries.add(e);
    }
    return entries;
  }
}
