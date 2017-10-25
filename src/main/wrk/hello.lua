token = nil
path  = "/ms-auth/jwt/login?userType=customer-mobile&userName=1234567890123&passWord=123"

request = function()
   return wrk.format("GET", path)
end

response = function(status, headers, body)
   if not token and status == 200 then
      token = headers["JWT-TOKEN"]
      path  = "/ms-demo/v1/hello"
      wrk.headers["JWT-TOKEN"] = token
   end
end
