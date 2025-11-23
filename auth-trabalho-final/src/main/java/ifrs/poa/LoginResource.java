package ifrs.poa;

import java.util.Arrays;
import java.util.HashSet;

import org.eclipse.microprofile.jwt.Claims;

import io.smallrye.jwt.build.Jwt;
import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/login")
public class LoginResource {
    private static final String ISSUER = "users-issuer";

    @POST
    @PermitAll
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public String login(@FormParam("username") String username,
            @FormParam("email") String email,
            @FormParam("password") String password) {
        return Jwt.issuer(ISSUER)
                .upn(email)
                .groups(new HashSet<>(Arrays.asList("User")))
                .claim(Claims.nickname, username)
                .sign();
    }
}