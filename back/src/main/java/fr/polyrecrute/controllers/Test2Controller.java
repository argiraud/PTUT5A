package fr.polyrecrute.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lol")
@Tag(name = "Test2Controller", description = "the Test2Controller API")
public class Test2Controller {

    @Operation(summary = "Get a book sdsdf its id", description = "Returns a sdfsdf contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found sdf book",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "sdcsdcsscd id supplied"),
            @ApiResponse(responseCode = "404", description = "Book notsdc found") })
    @GetMapping(value = "", produces = { "application/json"})
    public String hello() {
        //userEntityDao.save(new UserEntity("login", "password", 0));
        return "lol";

    }
}