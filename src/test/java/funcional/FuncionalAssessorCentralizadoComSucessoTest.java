package funcional;

import basetest.AssessorPedagogicoBaseTest;
import basetest.BaseDataFactory;
import basetest.ContratoAssessorCentralizadoBaseTest;
import basetest.EmpresaBaseTest;
import com.aventstack.extentreports.testng.listener.ExtentITestListenerClassAdapter;
import dto.*;
import io.sicredi.tm4j.common.annotations.TestCase;
import io.sicredi.tm4j.testng.TM4JTestNGListener;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.GeradorDatas;
import utils.GeradorNomeDataRg;

import java.io.File;

import static org.hamcrest.Matchers.*;

@Listeners({TM4JTestNGListener.class, ExtentITestListenerClassAdapter.class})
public class FuncionalAssessorCentralizadoComSucessoTest extends BaseDataFactory {
    String tokenValido;
    AssessorPedagogicoBaseTest assessorPedagogico= new AssessorPedagogicoBaseTest();
    EmpresaRequestDTO empresaRequestDTO=new EmpresaRequestDTO();
    EmpresaBaseTest empresaBaseTest=new EmpresaBaseTest();
    Integer idEmpresa;
    AssessorCentralizadoRequestDTO assessorCentralizadoRequestDTO= new AssessorCentralizadoRequestDTO();
    ContratoAssessorCentralizadoBaseTest contratoAssessorCentralizadoBaseTest = new ContratoAssessorCentralizadoBaseTest();
    AssessorPedagogicoRequestDTO assessorPedagogicoRequestDTO = new AssessorPedagogicoRequestDTO();
    AnaliseOperacionalResquestDTO analiseOperacionalResquestDTO = new  AnaliseOperacionalResquestDTO();
    BuscaContratoVigenteRequestDTO buscaContratoVigenteRequestDTO = new  BuscaContratoVigenteRequestDTO();
    Integer idContrato;
    Integer idAditivo;
    Integer idDistrato;



    @BeforeTest(groups = "funcional-assessor-centralizado-com-sucesso", description = "Cria base da escola")
    public void geraToken_CadastraEmpresa() {
        tokenValido = assessorPedagogico.token("password", "stefany_eduarda", "teste123", "openid")
                .statusCode(HttpStatus.SC_OK)
                .extract().response().jsonPath().getString("access_token");
        empresaRequestDTO = empresaDataFactory.cadastraEmpresa("Rubem Berta", "91250554", "Porto Alegre"
                , new GeradorNomeDataRg().geradorCnpj(false), "Rua Paulo Renato", "Predio 1", 23, "Empresa Automação" + new GeradorNomeDataRg().geradorNomes(),
                "440");
        empresaBaseTest.postCadastraEmpresa(tokenValido, empresaRequestDTO).statusCode(HttpStatus.SC_CREATED);
        idEmpresa = empresaBaseTest.getConsultaEmpresaPorCnpj(tokenValido, empresaRequestDTO.getCnpjEmpresa())
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().getInt("id");

        assessorPedagogicoRequestDTO = assessorPedagogicoDataFactory.cadastroAssessor("Assessor Automacao" + new GeradorNomeDataRg().geradorNomes(), new GeradorNomeDataRg().geraCPF(),
                "stefany_eduarda@terceiros.sicredi.com.br", "51982223134", idEmpresa, new String[]{"0101"}, new String[]{""}, "stefany_eduarda", 1,
                "Criacao", 1, "pedagogia", "20010606", new Integer[]{2});

        assessorPedagogico.putCadastraAssessor(tokenValido, assessorPedagogicoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
                .body("idAssessor", is(assessorPedagogicoRequestDTO.getCpf()));
    }

    @TestCase(key = "ADM08-T1170",keyCycle = "ADM08-C1888")
    @Test(groups = "funcional-assessor-centralizado-com-sucesso",priority = 1,description = "Validar o cadastro do contrato assessor centralizado")
    public void validarCadastroContratoComSucessoTest(){
        assessorCentralizadoRequestDTO= contratoAssessorCentralizadoDataFactory
                .cadastroContratoAssessorCentralizado("0101",
                        new String[]{assessorPedagogicoRequestDTO.getCpf()},
                        null,
                        "31122024",
                        "21112023",
                "stefany_eduarda@terceiros.sicredi.com.br",
                null,
                        null,
                null,
                idEmpresa,
                empresaRequestDTO.getCnpjEmpresa()
                ,1,
                        12,
                        10.50,
                        20.50,
                        30.50,
                        55.50,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
           idContrato= contratoAssessorCentralizadoBaseTest.
                    postCadastroContratoAssessorCentralizado(tokenValido,assessorCentralizadoRequestDTO)
                    .statusCode(HttpStatus.SC_OK)
                    .extract().response().jsonPath().getInt("id");


    }



    @TestCase(key = "ADM08-T1171",keyCycle ="ADM08-C1888")
    @Test(groups = "funcional-assessor-centralizado-com-sucesso",priority = 2,description = "Edição do contrato")
    public void validarEdicaoContratoAssessorComSucessoTest(){
        assessorCentralizadoRequestDTO= contratoAssessorCentralizadoDataFactory
                .cadastroContratoAssessorCentralizado("0101",
                        new String[]{assessorPedagogicoRequestDTO.getCpf()},
                        null,
                        "31122024",
                        "21112023",
                        "stefany_eduarda@terceiros.sicredi.com.br",
                        null,
                        null,
                        idContrato,
                        idEmpresa,
                        empresaRequestDTO.getCnpjEmpresa()
                        ,1,
                        12,
                        20.50,
                        30.50,
                        30.50,
                        55.55,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
         contratoAssessorCentralizadoBaseTest.
                postCadastroContratoAssessorCentralizado(tokenValido,assessorCentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK);


    }
    @TestCase(key = "ADM08-T1176",keyCycle ="ADM08-C1888")
    @Test(groups = "funcional-assessor-centralizado-com-sucesso",priority = 3,description = "Analise Operacional do Contrato")
    public void validarCadastroContratoEmAnaliseOperacionalComSucessoTest(){
        analiseOperacionalResquestDTO=contratoAssessorCentralizadoDataFactory.
                analiseOperacionalAssessorCentralizado(idContrato,1,"tudo certo","");
        contratoAssessorCentralizadoBaseTest.postAnaliseOperacionalContratoAssessorCentralizado(tokenValido,analiseOperacionalResquestDTO)
                .statusCode(HttpStatus.SC_CREATED);

    }
    @TestCase(key = "ADM08-T1177",keyCycle ="ADM08-C1888")
    @Test(groups ="funcional-assessor-centralizado-com-sucesso",priority = 4,description = "Contrato assinado enviando o upload")
    public void validarUploadContratoAssinadoComSucessoTest(){
            contratoAssessorCentralizadoBaseTest.postContratoAssinado(tokenValido,idContrato, new File("src/main/java/documents/teste.pdf"))
                    .statusCode(HttpStatus.SC_OK);


    }
    @TestCase(key = "ADM08-T1169",keyCycle = "ADM08-C1888")
    @Test(groups = "funcional-assessor-centralizado-com-sucesso",priority = 5,description = "Consulta de contratos vigentes")
    public void validarConsultaContratoVigenteComSucessoTest(){
        buscaContratoVigenteRequestDTO= contratoAssessorCentralizadoDataFactory.
                buscaContratoVigenteAssessorCentralizado("0101","",new GeradorDatas().dataContratoVigente());
        contratoAssessorCentralizadoBaseTest.postBuscaContratoVigente(tokenValido,buscaContratoVigenteRequestDTO)
                .statusCode(HttpStatus.SC_OK);

    }


    @TestCase(key = "ADM08-T1172",keyCycle = "ADM08-C1888")
    @Test(groups = "funcional-assessor-centralizado-com-sucesso",priority = 6,description = "Cadastro de aditivo do contrato")
    public void validarCadastroAditivoContratoAssessorComSucessoTest(){
        assessorCentralizadoRequestDTO= contratoAssessorCentralizadoDataFactory
                .cadastroContratoAssessorCentralizado("0101",
                        new String[]{assessorPedagogicoRequestDTO.getCpf()},
                        null,
                        "31122025",
                        "21112023",
                        "stefany_eduarda@terceiros.sicredi.com.br",
                        null,
                        null,
                        idContrato,
                        idEmpresa,
                        empresaRequestDTO.getCnpjEmpresa()
                        ,2,
                        12,
                        20.50,
                        30.50,
                        30.50,
                        55.55,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
     idAditivo=  contratoAssessorCentralizadoBaseTest.
                postCadastroContratoAssessorCentralizado(tokenValido,assessorCentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
             .extract().response().jsonPath().getInt("id");

    }


    @TestCase(key = "ADM08-T1173",keyCycle = "ADM08-C1888")
    @Test(groups = "funcional-assessor-centralizado-com-sucesso",priority = 7,description = "Edição do aditivo")
    public void validarEdicaoAditivoComSucessoTest(){
        assessorCentralizadoRequestDTO= contratoAssessorCentralizadoDataFactory
                .cadastroContratoAssessorCentralizado("0101",
                        new String[]{assessorPedagogicoRequestDTO.getCpf()},
                        null,
                        "31122025",
                        "21112023",
                        "stefany_eduarda@terceiros.sicredi.com.br",
                        idAditivo,
                        null,
                        idContrato,
                        idEmpresa,
                        empresaRequestDTO.getCnpjEmpresa()
                        ,2,
                        12,
                        30.50,
                        30.50,
                        40.50,
                        55.55,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
         contratoAssessorCentralizadoBaseTest.
                postCadastroContratoAssessorCentralizado(tokenValido,assessorCentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK);


    }
    @Test(groups = "funcional-assessor-centralizado-com-sucesso",priority = 8,description = "Analise operacional do contrato")
    public void validarCadastrarAnaliseOperacionalAditivoComSucessoTest(){
        analiseOperacionalResquestDTO=contratoAssessorCentralizadoDataFactory.
                analiseOperacionalAssessorCentralizado(idAditivo,2,"tudo certo","");
        contratoAssessorCentralizadoBaseTest.postAnaliseOperacionalContratoAssessorCentralizado(tokenValido,analiseOperacionalResquestDTO)
                .statusCode(HttpStatus.SC_CREATED);


    }

    @TestCase(key = "ADM08-T1178",keyCycle = "ADM08-C1888")
    @Test(groups = "funcional-assessor-centralizado-com-sucesso",priority = 9,description = "Aditivo assinado mudando o status do contrato")
    public void validarUploadAditivoContratoAssessorAssinadoComSucessoTest(){
        contratoAssessorCentralizadoBaseTest.postContratoAssinadoAditivo(tokenValido,idAditivo, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);

    }

    @TestCase(key = "ADM08-T1174",keyCycle = "ADM08-C1888")
    @Test(groups = "funcional-assessor-centralizado-com-sucesso",priority = 10,description = "Cadastro do distrato do contrato")
    public void validarCadastroDistratoContratoAssessorComSucessoTest(){
        assessorCentralizadoRequestDTO= contratoAssessorCentralizadoDataFactory
                .cadastroContratoAssessorCentralizado("0101",
                        new String[]{assessorPedagogicoRequestDTO.getCpf()},
                        "31122024",
                        "31122025",
                        "21112023",
                        "stefany_eduarda@terceiros.sicredi.com.br",
                        idAditivo,
                        null,
                        idContrato,
                        idEmpresa,
                        empresaRequestDTO.getCnpjEmpresa()
                        ,3,
                        12,
                        30.50,
                        30.50,
                        40.50,
                        55.55,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
        idDistrato=  contratoAssessorCentralizadoBaseTest.
                postCadastroContratoAssessorCentralizado(tokenValido,assessorCentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK)
            .extract().response().jsonPath().getInt("id");
    }
    @TestCase(key = "ADM08-T1175",keyCycle = "ADM08-C1888")
    @Test(groups = "funcional-assessor-centralizado-com-sucesso",priority = 11,description = "Edição do Contrado")
    public void validarEdicaoDistratoComSucessoTest(){
        assessorCentralizadoRequestDTO= contratoAssessorCentralizadoDataFactory
                .cadastroContratoAssessorCentralizado("0101",
                        new String[]{assessorPedagogicoRequestDTO.getCpf()},
                        "31122024",
                        "31122025",
                        "21112023",
                        "stefany_eduarda@terceiros.sicredi.com.br",
                        idAditivo,
                        idDistrato,
                        idContrato,
                        idEmpresa,
                        empresaRequestDTO.getCnpjEmpresa()
                        ,3,
                        12,
                        30.50,
                        30.50,
                        40.50,
                        55.55,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    contratoAssessorCentralizadoBaseTest.
                postCadastroContratoAssessorCentralizado(tokenValido,assessorCentralizadoRequestDTO)
                .statusCode(HttpStatus.SC_OK);

    }


    @Test(groups = "funcional-assessor-centralizado-com-sucesso",priority = 12,description = "Analise Operacional do distrato")
    public void validarCadastrarAnaliseOperacionalDistratoComSucessoTest(){
        analiseOperacionalResquestDTO=contratoAssessorCentralizadoDataFactory.
                analiseOperacionalAssessorCentralizado(idDistrato,3,"tudo certo","");
        contratoAssessorCentralizadoBaseTest.postAnaliseOperacionalContratoAssessorCentralizado(tokenValido,analiseOperacionalResquestDTO)
                .statusCode(HttpStatus.SC_CREATED);

    }


    @TestCase(key = "ADM08-T1179",keyCycle = "ADM08-C1888")
    @Test(groups = "funcional-assessor-centralizado-com-sucesso",priority = 13,description = "Distrato assinado mudando a data fim do contrato")
    public void validarUploadDistratoAssinadoComSucessoTest(){
        contratoAssessorCentralizadoBaseTest.postContratoAssinadoDistrato(tokenValido,idDistrato, new File("src/main/java/documents/teste.pdf"))
                .statusCode(HttpStatus.SC_OK);

    }


    @Test(groups = "funcional-assessor-centralizado-com-sucesso",priority = 14,description = "Consulta contrato generico")
    public void validarConsultaContratoComSucessoTest(){
        contratoAssessorCentralizadoBaseTest.getBuscaContratoGenerico(tokenValido,idContrato,1)
                .statusCode(HttpStatus.SC_OK)
                .body("cpfAssessores",hasItems(assessorCentralizadoRequestDTO.getCpfAssessores()))
                .body("codCoop",is("0101"))
                .body("emailCoop",is("stefany_eduarda@terceiros.sicredi.com.br"));

    }





}
