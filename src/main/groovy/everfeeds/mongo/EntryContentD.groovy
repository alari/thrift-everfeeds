@Typed package everfeeds.mongo;


import com.google.code.morphia.annotations.Entity
import com.google.code.morphia.annotations.Id
import com.google.code.morphia.annotations.Version
import everfeeds.thrift.domain.EntryContent
import org.bson.types.ObjectId

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:40
 */
@Entity
public class EntryContentD {
  @Id
  ObjectId id;

  @Version
  Long version;

  String html;

  public void syncFromThrift(EntryContent entryContent) {
    html = entryContent.html;
  }

  public void syncToThrift(EntryContent entryContent) {
    entryContent.html = html;
    entryContent.id = id.toString();
  }
}
