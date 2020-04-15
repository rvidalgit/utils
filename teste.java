package br.com.tauto.parceiro.auth.controller;

import br.com.tauto.parceiro.auth.AuthServerMain;
import br.com.tauto.parceiro.auth.filter.EncodingFilter;
import br.com.tauto.parceiro.auth.util.AuthTestUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServerMain.class)
@Sql("/sql/testUsers.sql")
public class ConviteControllerTest {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation("build/generated-snippets");

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Value("${jwt.route.convite.view}")
    private String convite; //visualizar convite

    @Value("${jwt.route.convite.registro}")
    private String registro; //finalizar cadastro

    @Value("${jwt.route.convite.contador}")
    private String conviteContador; //aceitar convite

    @Value("${jwt.route.convite.parceiro}")
    private String conviteParceiro; //aceitar convite

    @Autowired
    private EncodingFilter encodingFilter;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .addFilters(encodingFilter)
                .apply(documentationConfiguration(this.restDocumentation).operationPreprocessors()
                        .withRequestDefaults(prettyPrint())
                        .withResponseDefaults(prettyPrint()))
                .build();
    }

    @Test
    public void testAtivarPreCadastro() throws Exception {
        String hash = "7399B736EB0D9410FEB681854CAA77FF";
        String body = mockMvc
                .perform(AuthTestUtil.criarRequestNewUserConvite("1e835b56-fbb4-4c69-a4f2-9e52debf6c5d", "teste@teste.com", "12345678", "Teste", "Ativar", registro, hash, "87.608.726/0001-90", null))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(document("{class-name}/{method-name}", requestFields(
                        fieldWithPath("firstName").description("O nome do usuário.")
                                .attributes(key("constraints")
                                        .value("Não pode ser nulo.Não pode ser vazio.")),
                        fieldWithPath("lastName").description("O último nome do usuário."),
                        fieldWithPath("email").description("O endereço de e-mail do usuário.")
                                .attributes(key("constraints")
                                        .value("Campo obrigatório e email valido")),
                        fieldWithPath("password").description("A senha do usuário.")
                                .attributes(key("constraints")
                                        .value("Campo obrigatório")),
                        fieldWithPath("uuid").description("A identificação do usuário.")
                                .attributes(key("constraints")
                                        .value("Campo obrigatório")),
                        fieldWithPath("enabled").description("Usuário habilitado.")
                                .attributes(key("constraints")
                                        .value("Campo obrigatório")),
                        fieldWithPath("perfis").description("Lista de perfis do usuário (ROLE).")
                                .attributes(key("constraints")
                                        .value("Campo obrigatório")),
                        fieldWithPath("token").ignored(),
                        fieldWithPath("dispositivo").ignored(),
                        fieldWithPath("tributacao").ignored(),
                        fieldWithPath("municipio").ignored(),
                        fieldWithPath("simplesNacional").ignored(),
                        fieldWithPath("naturezaOperacao").ignored(),
                        fieldWithPath("documento").description("CPF/CNPJ do usuário.")
                                .attributes(key("constraints").value("Campo obrigatório para profissional parceiro")),
                        fieldWithPath("inscricaoMunicipal").description("Inscrição Municipal do usuário.")
                                .attributes(key("constraints").value("Campo obrigatório para profissional parceiro")),
                        fieldWithPath("enquadramento").ignored()
                        ),
                        responseFields(
                                fieldWithPath("uuid").description("Identificador do usuário."),
                                fieldWithPath("email").description("E-mail do usuário."),
                                fieldWithPath("firstName").description("Nome do usuário."),
                                fieldWithPath("lastName").description("Último nome do usuário."),
                                fieldWithPath("documento").description("CPF ou CNPJ do usuário."),
                                fieldWithPath("tributacao").description("Regime de tributação do parceiro. [5 - MEI, 6 - ME_EPP]"),
                                fieldWithPath("municipio").description("Domicilio tributário do parceiro."),
                                fieldWithPath("inscricaoMunicipal").description("Inscrição Municipal do usuário."),
                                fieldWithPath("perfis.[].perfil").description("Lista de perfis do usuário (ROLE)."),
                                fieldWithPath("token").ignored(),
                                fieldWithPath("simplesNacional").ignored(),
                                fieldWithPath("naturezaOperacao").ignored(),
                                fieldWithPath("enquadramento").ignored()
                        )
                ))
                .andReturn().getResponse().getContentAsString();
    }

}
