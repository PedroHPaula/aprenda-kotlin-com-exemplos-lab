// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario(val nome: String, val email: String, var pontos: Double) {
    override fun toString(): String = "Nome: ${nome} - Pontuação: ${pontos}"
}

data class ConteudoEducacional(val nome: String, val duracao: Int, val nivel: Nivel)

data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>, val nivel: Nivel) {

    val inscritos = mutableListOf<Usuario>()

    // Checa se o email do usuário passado já está cadastrado
    private fun checkEmail(usuario: Usuario): Boolean  = inscritos.any { it.email == usuario.email }

    // Retorna todos os inscritos por ordem alfabética
    fun listarInscritosPorNome(): List<Usuario> = inscritos.sortedBy { it.nome }

    // Retorna todos os inscritos por ordem decrescente de pontuação
    fun listarInscritosPorPontos(): List<Usuario> = inscritos.sortedByDescending { it.pontos }
    
    fun matricular(usuario: Usuario) {
        // Cadastra o usuário apenas se seu email já não foi cadastrado
        if(!checkEmail(usuario)) {
            inscritos.add(usuario)
            println("O(a) aluno(a):")
            println("Nome: ${usuario.nome}")
            println("E-mail: ${usuario.email}")
            println("Foi matriculado(a) na formação ${nome} com sucesso!\n")
        } else {
            println("O endereço de e-mail ${usuario.email} já está cadastrado na formação ${nome}\n")
        }
    }

    // Calcula a duração total em horas da formação
    fun calcularDuracao(): Int = conteudos.sumOf { it.duracao } / 60

    // Retorna os conteúdos pelo nível
    fun conteudosPorNivel(nivel: Nivel): List<ConteudoEducacional> = conteudos.filter { it.nivel == nivel }

    // Lista todos os conteúdos classificados por nível
    fun listarConteudosPorNivel() = Nivel.entries.forEach {
        println("Conteúdos de Nível ${it}:")
        conteudosPorNivel(it).forEach { println("${it.nome} - ${it.duracao/60} hrs") }
    }

    // Lista todos os conteúdos classificados por duração
    fun listarConteudosPorDuracao() {
        println("Conteúdos por Duração:")
        conteudos.sortedByDescending { it.duracao }
                .forEach{ println("${it.nome} - ${it.duracao/60} hrs") }
    }
}

fun main() {
    // Usuários de teste
    val testUser1 = Usuario("Pedro de Paula", "pedrohpaula98@gmail.com", 0.00)
    val testUser2 = Usuario("Pedro Henrique de Paula", "pedrohpaula98@gmail.com", 0.00)
    val testUser3 = Usuario("João Mendes", "joao_mendes@hotmail.com", 1200.50)
    val testUser4 = Usuario("Wani Nascimento", "AWani.Nascimento@yahoo.com", 1200.49)
    val testUser5 = Usuario("Gabriel Machado", "G4bri3lM4ch4d0@gmail.com", 250.00)
    val testUser6 = Usuario("Josiel Soares", "_JO_Soares@outlook.com", 1200.51)

    // Conteúdos de teste
    val conteudo1 = ConteudoEducacional("Aprendendo a Sintaxe Java", 5*60, Nivel.BASICO)
    val conteudo2 = ConteudoEducacional("Pilares da POO em Java", 2*60, Nivel.INTERMEDIARIO)
    val conteudo3 = ConteudoEducacional("Trabalhando com Collections Java", 4*60, Nivel.INTERMEDIARIO)
    val conteudo4 = ConteudoEducacional("Padrões de Projeto com Java", 2*60, Nivel.DIFICIL)
    val conteudo5 = ConteudoEducacional("Introdução à POO com Python", 1*60, Nivel.DIFICIL)
    val conteudo6 = ConteudoEducacional("Fundamentos de ETL com Python", 3*60, Nivel.INTERMEDIARIO)
    val conteudo7 = ConteudoEducacional("Análise da dados com Python e Pandas", 4*60, Nivel.INTERMEDIARIO)
    val conteudo8 = ConteudoEducacional("Rest APIs com Python e Flask", 5*60, Nivel.DIFICIL)

    val listaDeConteudosJava = listOf<ConteudoEducacional>(conteudo1, conteudo2, conteudo3, conteudo4)
    val listaDeConteudosPython = listOf<ConteudoEducacional>(conteudo5, conteudo6, conteudo7, conteudo8)

    // Instancia as formações com os conteúdos e matricula os alunos como teste
    val formacaoJava = Formacao("Java Developer", listaDeConteudosJava, Nivel.INTERMEDIARIO)
    formacaoJava.matricular(testUser1)
    formacaoJava.matricular(testUser2)
    formacaoJava.matricular(testUser3)

    val formacaoPython = Formacao("Python Developer", listaDeConteudosPython, Nivel.INTERMEDIARIO)
    formacaoPython.matricular(testUser4)
    formacaoPython.matricular(testUser5)
    formacaoPython.matricular(testUser6)

    println("*** Sobre a formação ${formacaoJava.nome} *** :")
    println("Duração - ${formacaoJava.calcularDuracao()} hrs")
    println("Nível -  ${formacaoJava.nivel}")
    formacaoJava.listarConteudosPorNivel()
    println("Total de alunos -  ${formacaoJava.inscritos.size}")
    println("Alunos da Formação ${formacaoJava.nome} por ordem alfabética:")
    formacaoJava.listarInscritosPorNome().forEach { println(it) }
    print("\n")
    println("*** Sobre a formação ${formacaoPython.nome} *** :")
    println("Duração - ${formacaoPython.calcularDuracao()} hrs")
    println("Nível -  ${formacaoPython.nivel}")
    formacaoPython.listarConteudosPorDuracao()
    println("Alunos da Formação ${formacaoPython.nome} por pontuação:")
    formacaoPython.listarInscritosPorPontos().forEach { println(it) }
}
