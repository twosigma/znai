package rest.headers

import static com.twosigma.testing.webtau.WebTauGroovyDsl.*

scenario("simple get") {
    http.get("/weather") {
        temperature.should == 88
    }
}