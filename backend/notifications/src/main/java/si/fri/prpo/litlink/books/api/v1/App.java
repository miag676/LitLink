package si.fri.prpo.litlink.books.api.v1;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;


@OpenAPIDefinition(
    info = @Info(
        title = "LitLink Notifications API",
        version = "1.0.0",
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0.html"
        ),
        description = "API for managing books in LitLink."
    )
)
@ApplicationPath("v1")
public class App extends Application {
}
