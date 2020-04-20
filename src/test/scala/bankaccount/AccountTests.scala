package bankaccount
import org.scalatest.FunSuite

class AccountTests extends FunSuite {
  // test 1
  test("Create a user Account") {
    val myAccount = AccountUser(List.empty, 1545157654)
    assert(myAccount.idBank == 1545157654)
  }

  // test 2
  test("Make a deposit") {
    val myAccount = AccountUser(List.empty, 1545157654)
    myAccount.deposit(100000)
    assert(myAccount.balance == 100000)
  }

  // test 3
  test("Make a withdrawal") {
    val myAccount = AccountUser(List.empty, 1545157654)
    myAccount.deposit(10000)
    myAccount.withdrawal(1000)
    assert(myAccount.balance == 9000)
  }
}
