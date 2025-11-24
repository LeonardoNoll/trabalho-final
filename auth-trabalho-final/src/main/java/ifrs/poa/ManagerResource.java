package ifrs.poa;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import ifrs.poa.client.ProjectsClient;
import ifrs.poa.models.Project;
import ifrs.poa.models.Status;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/manager")
public class ManagerResource {
    private final ProjectsClient projectsClient;

    @Inject
    public ManagerResource(@RestClient ProjectsClient projectsClient) {
        this.projectsClient = projectsClient;

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("User")
    @WithTransaction
    public Uni<Response> list() {

        return projectsClient.list();
    }

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("User")
    // @PermitAll
    @WithTransaction
    public Uni<Response> create(
            @FormParam("name") String name,
            @FormParam("description") String description,
            @FormParam("status") String status) {
        var logger = org.jboss.logging.Logger.getLogger(ManagerResource.class);
        logger.infof("ManagerResource.create called with name=%s, description=%s, status=%s", name, description,
                status);

        var project = new Project();
        project.setName(name);
        project.setDescription(description);
        try {
            project.setStatus(Status.valueOf(status));
        } catch (IllegalArgumentException | NullPointerException ex) {
            logger.errorf(ex, "Invalid status value: %s", status);
            return Uni.createFrom().failure(ex);
        }

        logger.debugf("Sending project to ProjectsClient.create(): %s", project);
        return projectsClient.create(project)
                .onItem()
                .invoke(response -> logger.infof("ProjectsClient.create returned status=%d", response.getStatus()))
                .onFailure().invoke(err -> logger.error("ProjectsClient.create failed", err));
    }

}
