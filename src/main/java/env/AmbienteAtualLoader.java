package env;

import enums.Ambiente;
import org.apache.commons.lang3.StringUtils;

import static enums.Ambiente.DEV;
import static enums.Ambiente.HOM;

public class AmbienteAtualLoader {

        private static final String DEFAULT_ENV = "dev";

        public static Ambiente get() {
            String env = System.getProperty(DEFAULT_ENV, DEFAULT_ENV);

            return switch (env) {
                case "dev" -> DEV;
                case "hom" -> HOM;
                default ->
                        throw new IllegalArgumentException("Ambiente '" + env + "' não configurado para execução dos testes");
            };
        }
    }