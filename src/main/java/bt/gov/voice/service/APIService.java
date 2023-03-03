package bt.gov.voice.service;

import bt.gov.voice.dao.TokenDAO;
import bt.gov.voice.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Base64;
import java.util.ResourceBundle;

/**
 * Created by Deepak on 12/18/2020.
 */
@Service
@Transactional
public class APIService {
    @Autowired
    private TokenDAO tokenRepository;


    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    public Token getApplicationToken() throws ParseException {
        Token token = tokenRepository.findToken();

        if(null == token) {
            token = generateToken();
        } else {
            float secondsElapsed = (System.currentTimeMillis() - token.getCreated_on()) / 1000F;
            if(secondsElapsed > token.getExpires_in()) {
                tokenRepository.deleteAll();
                token = generateToken();
            } else {
                return token;
            }
        }
        return token;
    }

    private Token generateToken() {
        ResourceBundle resourceBundle1 = ResourceBundle.getBundle("wsEndPointURL_en_US");
        String dataHubEndPointUrl =resourceBundle1.getString("getDatahubToken.endPointURL");
        String consumerKey = resourceBundle1.getString("CONSUMER.KEY");
        String consumerSecret = resourceBundle1.getString("CONSUMER.SECRET");

        Token token = new Token();
        String authStringEnc = Base64.getEncoder().encodeToString((consumerKey+":"+consumerSecret).getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic "+authStringEnc);
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<Token> response = restTemplate.exchange(dataHubEndPointUrl, HttpMethod.POST, request, Token.class);
        token.setAccess_token(response.getBody().getAccess_token());
        token.setExpires_in(response.getBody().getExpires_in());
        token.setScope(response.getBody().getScope());
        token.setToken_type(response.getBody().getToken_type());
        token.setCreated_on(System.currentTimeMillis());
        tokenRepository.save(token);
        return token;
    }
}
