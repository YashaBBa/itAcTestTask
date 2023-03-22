package entity.mapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import entity.User;

import java.io.IOException;

public class CustomUserSerializer extends JsonSerializer<User> {


    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeObject(user.getSurname() + " " + user.getName() + " " + user.getPatronymic() + " " + user.getEmail()
                + " " + user.getRole().getRoles().name());
    }
}
