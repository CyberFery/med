package uqam.team17.authenticationservice.token;

public interface Token {
    String getTokenData();
    void validate() throws Exception;
}
