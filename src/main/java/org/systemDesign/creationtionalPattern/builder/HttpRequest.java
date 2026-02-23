package org.systemDesign.creationtionalPattern.builder;

public class HttpRequest {
    private final String url;
    private final String method;
    private final String headers;
    private final String body;
    private final int timeout;

    private HttpRequest(HttpRequestBuilder builder){
        this.url = builder.url;
        this.method = builder.method;
        this.headers = builder.headers;
        this.body = builder.body;
        this.timeout = builder.timeout;
    }

    public static class HttpRequestBuilder {
        //Required
        private final String url;
        //Optional
        private String method = "GET";
        private String headers = "";
        private String body = "";
        private int timeout = 30;

        public HttpRequestBuilder(String url){
            this.url = url;
        }

        public HttpRequestBuilder method(String method){
            this.method = method;
            return this;
        }
        public HttpRequestBuilder headers(String headers){
            this.headers = headers;
            return this;
        }
        public HttpRequestBuilder body(String body){
            this.body = body;
            return this;
        }
        public HttpRequestBuilder timeout(int timeout){
            this.timeout = timeout;
            return this;
        }

        public HttpRequest build(){
            return new HttpRequest(this);
        }
    }

    public void execute() {
        System.out.println("Executing " + method + " request to " + url);
        System.out.println("Headers: " + headers);
        System.out.println("Body: " + body);
        System.out.println("Timeout: " + timeout + "s");
    }
}

class HttpMain{
    public static void main(String[] args) {
        HttpRequest request = new HttpRequest.HttpRequestBuilder("https://api.example.com/users")
                                                .method("POST")
                                                .headers("Content-Type: application/json")
                                                .body("{\"name\":\"John\"}")
                                                .timeout(60)
                                                .build();
        request.execute();

    }
}
