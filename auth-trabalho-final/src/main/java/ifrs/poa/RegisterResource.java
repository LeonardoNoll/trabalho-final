package ifrs.poa;

import ifrs.poa.models.User;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/register")
public class RegisterResource {
    // @POST
    // @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    // @Produces(MediaType.APPLICATION_JSON)
    // @Transactional
    // public String register(
    //         @FormParam("username") String username,
    //         @FormParam("email") String email,
    //         @FormParam("password") String password,
    //         @FormParam("confirmPassword") String confirmPassword) {
    //     checkEmailDuplicity(email);
    //     confirmPasswords(password, confirmPassword);
    //     persistUser(createUser(username, email, confirmPassword));
    //     return "Usuário " + username + " criado com sucesso";
    // }

    // private void checkEmailDuplicity(String email) {
    //     if (User.find("email", email) != null)
    //         throw new BadRequestException("Usuário com este email já existe");
    // }

    // private void confirmPasswords(String password, String confirmPassword) {
    //     if (!password.equals(confirmPassword))
    //         throw new BadRequestException("As senhas devem ser iguais");
    // }

    // // private User createUser(String username, String email, String password) {
    //    User user = new User();
    //    user.setUsername(username);
    //    user.setEmail(email);
    //    user.setPassword(password);
    //    return user;
    // // }

    // private String persistUser(User user) {
    //     user.persistAndFlush();
    //     return "Usuário criado com sucesso: " + user.toString();
    // }
}