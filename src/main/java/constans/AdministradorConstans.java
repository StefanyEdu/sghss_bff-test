package constans;
public class AdministradorConstans {
    //token
    public static String API_TOKEN="/oauth2/token";
    // agenda
    public static String ENPOINT_POST_CADASTRA_AGENDA_MEDICO="/v1/agenda/medico/";
    public static String ENPOINT_POST_CADASTRA_AGENDA_EXAME="v1/agenda/exames/";
    public static String ENPOINT_GET_CONSULTA_AGANDADAS_MARCADAS_POR_MEDICO="/v1/agenda/{idMedico}";
    public static String ENPOINT_GET_CONSULTA_AGANDADAS_MARCADAS_POR_PACIENTE="v1/agenda/marcada/{cpfPaciente}";
    //administrativo
    public static String ENPOINT_POST_CADASTRO_LEITO="v1/leito/";
    //paciente
    public static String ENPOINT_POST_CADASTRA_PACIENTE_PRONTO_ATENDIMENTO="v1/paciente/prontoatencimento/";

}
