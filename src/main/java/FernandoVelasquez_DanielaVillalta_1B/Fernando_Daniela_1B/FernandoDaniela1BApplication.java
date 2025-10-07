package FernandoVelasquez_DanielaVillalta_1B.Fernando_Daniela_1B;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FernandoDaniela1BApplication {

	public static void main(String[] args) {

		//Cargar el archivo .env sobre el application properties
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry ->
				System.setProperty(entry.getKey(), entry.getValue()));

		SpringApplication.run(FernandoDaniela1BApplication.class, args);
	}

}
