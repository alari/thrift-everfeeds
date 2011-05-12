namespace cpp everfeeds.thrift.error
namespace java everfeeds.thrift.error
namespace php everfeeds.thrift.error
namespace perl everfeeds.thrift.error
namespace st Thrift.Everfeeds.Error

exception TokenExpired {
  1: string errMsg,
}
exception TokenNotFound {
  1: string errMsg,
}
exception Forbidden {
  1: string errMsg,
}
exception NotFound {
  1: string errMsg,
}
exception WrongArgument {
  1: string errMsg,
}