package everfeeds.mongo;

import com.google.code.morphia.annotations.*;
import everfeeds.thrift.Tag;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * @author Dmitry Kurinskiy
 * @since 06.05.11 14:40
 */
@Entity
public class TagD {
  @Id
  public ObjectId id;

  @Reference
  public AccessD access;

  @Version
  public Long version;

  public String identity;
  public String title;

  public Date dateCreated = new Date();
  public Date lastUpdated = new Date();

  @PrePersist
  void prePersist() {
    lastUpdated = new Date();
  }

  public void syncToThrift(Tag tag){
    tag.id = id.toString();
    tag.identity = identity;
    tag.title = title;
    tag.accessId = access.id.toString();
  }

  public void syncFromThrift(Tag tag){
    identity = tag.identity;
    title = tag.title;
  }
}
