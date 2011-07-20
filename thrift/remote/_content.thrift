include "_types.thrift"

namespace java everfeeds.remote.thrift

struct Content {
  1: types.ContentType contentType;

  10: map<string,string> data;
  11: string html;
}