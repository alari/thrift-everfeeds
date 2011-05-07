package everfeeds.mongo;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Version;
import everfeeds.thrift.EntryContent;
import org.bson.types.ObjectId;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:40
 */
@Entity
public class EntryContentD {
  @Id
  public ObjectId id;

  @Version
  public Long version;

  public String content;

  public void syncFromThrift(EntryContent entryContent){
    content = entryContent.content;
  }

  public void syncToThrift(EntryContent entryContent){
    entryContent.content = content;
    entryContent.id = id.toString();
  }
}