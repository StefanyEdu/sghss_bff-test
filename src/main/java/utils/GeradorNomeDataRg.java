package utils;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Pattern;


public class GeradorNomeDataRg {
    public static final String DATA_SEM_BARRA = "ddMMyyyy";

    public String geradorNomes() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";

        Random random = new Random();

        String armazenaChaves = "";
        int index = -1;
        for (int i = 0; i < 9; i++) {
            index = random.nextInt(letras.length());
            armazenaChaves += letras.substring(index, index + 1);
        }
        return armazenaChaves;
    }

    public String geraRG() {

        String nDigResult;
        String numerosContatenados;
        String numeroGerado;
        Random numeroAleatorio = new Random();
        //numeros gerados
        int n1 = numeroAleatorio.nextInt(10);
        int n2 = numeroAleatorio.nextInt(10);
        int n3 = numeroAleatorio.nextInt(10);
        int n4 = numeroAleatorio.nextInt(10);
        int n5 = numeroAleatorio.nextInt(10);
        int n6 = numeroAleatorio.nextInt(10);
        int n7 = numeroAleatorio.nextInt(10);
        int n8 = numeroAleatorio.nextInt(10);
        int n9 = numeroAleatorio.nextInt(10);
        int n10 = numeroAleatorio.nextInt(10);

        //Conctenando os numeros
        numerosContatenados = String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3) + String.valueOf(n4) +
                String.valueOf(n5) + String.valueOf(n6) + String.valueOf(n7) + String.valueOf(n8) +
                String.valueOf(n9) + String.valueOf(n10);
        numeroGerado = numerosContatenados;

        return numeroGerado;

    }

    private int randomiza(int n) {
        int ranNum = (int) (Math.random() * n);
        return ranNum;
    }

    private int mod(int dividendo, int divisor) {
        return (int) Math.round(dividendo - (Math.floor(dividendo / divisor) * divisor));
    }

    public String geraCPF() {
        int n = 9;
        int n1 = randomiza(n);
        int n2 = randomiza(n);
        int n3 = randomiza(n);
        int n4 = randomiza(n);
        int n5 = randomiza(n);
        int n6 = randomiza(n);
        int n7 = randomiza(n);
        int n8 = randomiza(n);
        int n9 = randomiza(n);
        int d1 = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10;

        d1 = 11 - (mod(d1, 11));

        if (d1 >= 10)
            d1 = 0;

        int d2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11;

        d2 = 11 - (mod(d2, 11));

        String retorno = null;
        return retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + d1 + d2;
    }

    public String geradorCnpj(boolean comPontos) {
        int n = 9;
        int n1 = randomiza(n);
        int n2 = randomiza(n);
        int n3 = randomiza(n);
        int n4 = randomiza(n);
        int n5 = randomiza(n);
        int n6 = randomiza(n);
        int n7 = randomiza(n);
        int n8 = randomiza(n);
        int n9 = 0; //randomiza(n);
        int n10 = 0; //randomiza(n);
        int n11 = 0; //randomiza(n);
        int n12 = 1; //randomiza(n);
        int d1 = n12 * 2 + n11 * 3 + n10 * 4 + n9 * 5 + n8 * 6 + n7 * 7 + n6 * 8 + n5 * 9 + n4 * 2 + n3 * 3 + n2 * 4 + n1 * 5;

        d1 = 11 - (mod(d1, 11));

        if (d1 >= 10)
            d1 = 0;

        int d2 = d1 * 2 + n12 * 3 + n11 * 4 + n10 * 5 + n9 * 6 + n8 * 7 + n7 * 8 + n6 * 9 + n5 * 2 + n4 * 3 + n3 * 4 + n2 * 5 + n1 * 6;

        d2 = 11 - (mod(d2, 11));

        if (d2 >= 10)
            d2 = 0;

        String retorno = null;

        if (comPontos)
            retorno = "" + n1 + n2 + "." + n3 + n4 + n5 + "." + n6 + n7 + n8 + "/" + n9 + n10 + n11 + n12 + "-" + d1 + d2;
        else
            retorno = "" + n1 + n2 + n3 + n4 + n5 + n6 + n7 + n8 + n9 + n10 + n11 + n12 + d1 + d2;

        return retorno;
    }

    public String codigoCRM() {

        String nDigResult;
        String numerosContatenados;
        String numeroGerado;
        Random numeroAleatorio = new Random();
        //numeros gerados
        int n1 = numeroAleatorio.nextInt(10);
        int n2 = numeroAleatorio.nextInt(10);
        int n3 = numeroAleatorio.nextInt(10);
        int n4 = numeroAleatorio.nextInt(10);
        int n5 = numeroAleatorio.nextInt(10);

        //Conctenando os numeros
        numerosContatenados = String.valueOf(n1) + String.valueOf(n2) + String.valueOf(n3) + String.valueOf(n4) +
                String.valueOf(n5);
        numeroGerado = numerosContatenados;

        return numeroGerado;

    }

    public String dataNascimento() {
        LocalDate dataInicio = LocalDate.now();
        String dataInicioFormatado = dataInicio.format(formatacaoDatas());
        return dataInicioFormatado;
    }

    private DateTimeFormatter formatacaoDatas() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATA_SEM_BARRA);
        return formatter;
    }
    private String correcaoAcento(String input) {
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(normalized).replaceAll("");
    }

    public String respostaEsperadaApiSemAcento(String mensagemEsperada){
        return correcaoAcento(mensagemEsperada);
    }
    public String respostaApiSemAcento(String mensagemEsperada){
        return correcaoAcento(mensagemEsperada);
    }


}
