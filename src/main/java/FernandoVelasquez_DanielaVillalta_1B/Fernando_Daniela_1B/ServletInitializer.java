package FernandoVelasquez_DanielaVillalta_1B.Fernando_Daniela_1B;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FernandoDaniela1BApplication.class);
	}

}
