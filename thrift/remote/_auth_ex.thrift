namespace java everfeeds.remote.auth.thrift.ex

exception AuthMethodMismatch {
  1: string msg;
}
exception AuthSystemUnknown {
  1: string msg;
}
exception AuthFailed {
  1: string msg;
}

exception AuthConnectionError {
  1: string msg;
}