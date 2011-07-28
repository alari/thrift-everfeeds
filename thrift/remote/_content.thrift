include "_types.thrift"

namespace java everfeeds.remote.discovery.thrift

struct Content {
  1: _types.ContentType contentType;

  10: map<string,string> data;
  11: string html;
}