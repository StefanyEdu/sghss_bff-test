package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeradorDatas {
    public String dataInicioInscricaoPadrao(){
        LocalDateTime dataInicio = LocalDateTime.now();
        // Criando um formato para a string de data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String dataFormatada = dataInicio.format(formatter);
        return dataFormatada;

    }
    public String dataContratoVigente(){
        LocalDateTime dataInicio = LocalDateTime.now();
        // Criando um formato para a string de data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = dataInicio.format(formatter);
        return dataFormatada;

    }
    public String dataInicio(){
        LocalDateTime dataInicio = LocalDateTime.now();
        // Criando um formato para a string de data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dataFormatada = dataInicio.format(formatter);
        return dataFormatada;

    }
    public String dataInicioNaoIniciado(){
        LocalDateTime dataInicio = LocalDateTime.now();
        // Criando um formato para a string de data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataFormatada = dataInicio.plusDays(1).format(formatter);
        return dataFormatada;

    }
    public String dataFim(){
        LocalDateTime dataFim = LocalDateTime.now();
        // Criando um formato para a string de data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dataFimInscricao=dataFim.plusMonths(13).format(formatter);
        return dataFimInscricao;

    }
    public  String dataFimInscricaoPadrao(){
        LocalDateTime dataFim = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String dataFimInscricao=dataFim.plusMonths(12).format(formatter);
        return dataFimInscricao;
    }
    public  String dataFimInscricaoPadraoAditivo(){
        LocalDateTime dataFim = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String dataFimInscricao=dataFim.plusMonths(13).format(formatter);
        return dataFimInscricao;
    }
    public String dataInicioContrato(){
        // Criando um formato para a string de data
        LocalDateTime dataInicio = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataFormatada = dataInicio.format(formatter);
        return dataFormatada;

    }
    public String dataFimContrato(){
        // Criando um formato para a string de data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataFormatada = calculoDataFim().format(formatter);
        return dataFormatada;

    }
    private LocalDateTime calculoDataInicio() {
        LocalDateTime dataInicio = LocalDateTime.now();
        return dataInicio.plusDays(30);

    }
    private LocalDateTime calculoDataFim() {
        LocalDateTime dataFim = LocalDateTime.now();
        return dataFim.plusMonths(12);

    }
    public String dataRelatorio(){
        LocalDateTime dataInicio = LocalDateTime.now();
        // Criando um formato para a string de data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataFormatada = dataInicio.format(formatter);
        return dataFormatada;

    }
    
}
