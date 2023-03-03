package bt.gov.voice.dao;

import bt.gov.voice.base.baseDao;
import bt.gov.voice.entity.Token;
import org.springframework.stereotype.Repository;

/**
 * Created by Deepak on 12/18/2020.
 */
@Repository
public class TokenDAO extends baseDao {

    public Token findToken(){
        String sqlQuery = "SELECT id, access_token, created_on, expires_in, scope, token_type FROM token ORDER BY id DESC LIMIT 1";
        org.hibernate.Query hQuery = hibernateQueryToken(sqlQuery, Token.class);
        Token token = (Token) hQuery.uniqueResult();
        return token;
    }

    public void deleteAll() {
        String sqlQuery = "DELETE FROM token";
        org.hibernate.Query hQuery = hibernateQuery(sqlQuery);
        hQuery.executeUpdate();
    }

    public void save(Token token){
        if(token != null){
            saveOrUpdate(token);
        }
    }
}
