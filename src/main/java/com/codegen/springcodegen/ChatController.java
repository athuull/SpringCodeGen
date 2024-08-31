package com.codegen.springcodegen;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {
    // Instance variables for the chat client and the code generation service
    private final ChatClient chatClient;
    private final GeneratedCodeService codeService;

    /**
     * Constructor for ChatController.
     * Initializes the chat client with a specific system prompt and assigns the GeneratedCodeService.
     *
     * @param builder     The builder for creating the ChatClient instance.
     * @param codeService The service used to write generated code to files.
     */
    public ChatController(ChatClient.Builder builder, GeneratedCodeService codeService) {
        // Configure the chat client with a default system prompt
        this.chatClient = builder.defaultSystem("""
                        You are helpful AI assistant for writing code. Each class or method you are
                        asked to generate should have a supporting test class to cover that method or
                        methods. Please include each test in the result.
                        Please generate concise and readable code geared towards beginners.
                        """)
                .build();
        // Assign the code generation service
        this.codeService = codeService;

    }
    /**
     * Endpoint that triggers the chat client to generate a Java class with math operations,
     * including more than the basic 4 arithmetic operations. The generated class and its test
     * class are then written to files.
     *
     * @return The generated Code object containing the Java class and its test class.
     */
    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointment created successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Code.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Resource not found", content = @Content)})
    @Operation(summary = "Generate a Java class with math operations")
    public Code chat() {
        // Sends a prompt to the chat client to generate a Java class with advanced math operations
        Code code = chatClient.prompt()
                .user("""
                        Generate a Java class that contains math operations.
                        Please contain more than just the basic 4 arithmetic operations.
                        """)
                .call()
                .entity(Code.class);
        // Writes the generated code and its test class to files
        codeService.writeToFile(new String[]{code.code(), code.test()});
        // Returns the generated Code object

        return code;
    }
}