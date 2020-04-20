package bankaccount
import java.time.LocalDate

sealed trait Account {
  val id: BigInt
  def balance: Double
  def modifyBalance(quantity: Double): Account
  def deposit(quantity: Double): Account
  def withdrawal(quantity: Double): Account
  def printStatement: Unit
}

case class AccountStatement(date: LocalDate, amount: Double, balance: Double)

//case class AccountUser(balance, statements: List[AccountStatement], idBank: BigInt) extends Account {
case class AccountUser(statements: List[AccountStatement], idBank: BigInt) extends Account {
    val id = idBank
    private var history: List[AccountStatement] = statements
    def balance: Double =
      if (!history.isEmpty) history.head.balance
      else 0.0
    def modifyBalance(quantity: Double): Account = {
      val updatedBalance: Double = (balance + quantity)
      //AccountUser(updatedBalance, AccountStatement(LocalDate.now(), quantity, balance) +: statements, idBank)
      history = AccountStatement(LocalDate.now(), quantity, updatedBalance) +: history
      AccountUser(history, idBank)
    }

    def deposit(quantity: Double): Account =
      if (quantity > 0) modifyBalance(quantity)
      else this
    def withdrawal(quantity: Double): Account =
      if (balance < quantity || quantity < 0) this
      else modifyBalance(-quantity)
    def printStatement: Unit = {
      println("Date         Amount    Balance")
      //statements.foreach(s => println(s"${s.date}   ${s.amount}    ${s.balance}"))
      history.foreach(s => println(s"${s.date}   ${s.amount}    ${s.balance}"))
      println("\n")
    }
  }
}

object TestAccount extends App {
  var myAccount = AccountUser(List.empty, 1545157654)
  myAccount.printStatement

  // US 1
  //val updatedWithDeposit = myAccount.deposit(10000)
  //updatedWithDeposit.printStatement
  myAccount.deposit(10000)
  myAccount.printStatement

  // US 2
  // val updatedWithWithdrawal = updatedWithDeposit.withdrawal(1000).withdrawal(200)
  // updatedWithWithdrawal.printStatement
  myAccount.withdrawal(5000)
  myAccount.withdrawal(200)
  myAccount.printStatement
}
