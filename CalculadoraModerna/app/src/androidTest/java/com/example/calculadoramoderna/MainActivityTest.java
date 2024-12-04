package com.example.calculadoramoderna;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    // Rule to launch MainActivity before tests
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testTextViewContent() {
        // Verify initial state of input TextView (should be empty at the start)
        onView(withId(R.id.txtResultado)).check(matches(withText("")));

        // Verify initial state of output TextView (should be empty at the start)
        onView(withId(R.id.txtResultado)).check(matches(withText("")));
    }

    @Test
    public void testInputAfterButtonPress() {
        // Simulate button presses and check the input TextView
        onView(withId(R.id.btn8)).perform(click()); // Press "8"
        onView(withId(R.id.btnMais)).perform(click()); // Press "+"
        onView(withId(R.id.btn3)).perform(click()); // Press "3"

        // Verify input TextView shows "8.0 +"
        onView(withId(R.id.txtResultado)).check(matches(withText("8.0 +")));
        onView(withId(R.id.txtSubResultado)).check(matches(withText("3")));
    }

    @Test
    public void testOutputAfterCalculation() {
        // Simulate button presses to perform the addition
        onView(withId(R.id.btn8)).perform(click()); // Press "8"
        onView(withId(R.id.btnMais)).perform(click()); // Press "+"
        onView(withId(R.id.btn3)).perform(click()); // Press "3"

        onView(withId(R.id.txtResultado)).check(matches(withText("8.0 +")));
        onView(withId(R.id.txtSubResultado)).check(matches(withText("3")));

        onView(withId(R.id.btnIgual)).perform(click()); // Press "=" to calculate

        // Verify output TextView shows the result "11.0"
        onView(withId(R.id.txtResultado)).check(matches(withText("11.0")));
        onView(withId(R.id.txtSubResultado)).check(matches(withText("")));
    }

    @Test
    public void subtracao() {
        onView(withId(R.id.btn9)).perform(click());
        onView(withId(R.id.btnMenos)).perform(click());
        onView(withId(R.id.btn8)).perform(click());

        onView(withId(R.id.txtResultado)).check(matches(withText("9.0 -")));
        onView(withId(R.id.txtSubResultado)).check(matches(withText("8")));

        onView(withId(R.id.btnIgual)).perform(click());

        // verificar se a conta deu 1
        onView(withId(R.id.txtResultado)).check(matches(withText("1.0")));
        onView(withId(R.id.txtSubResultado)).check(matches(withText("")));
    }

    @Test
    public void divisao() {
        onView(withId(R.id.btn6)).perform(click());
        onView(withId(R.id.btnDivisao)).perform(click());
        onView(withId(R.id.btn5)).perform(click());
        onView(withId(R.id.btnIgual)).perform(click());

        // verificar se a conta deu 1.2
        onView(withId(R.id.txtResultado)).check(matches(withText("1.2")));
    }

    @Test
    public void limpar() {
        onView(withId(R.id.btn6)).perform(click());
        onView(withId(R.id.btnDivisao)).perform(click());
        onView(withId(R.id.btn5)).perform(click());
        onView(withId(R.id.btnLimpar)).perform(click());

        // verificar se os campos estão vazios
        onView(withId(R.id.txtResultado)).check(matches(withText("")));
        onView(withId(R.id.txtSubResultado)).check(matches(withText("")));

    }

    @Test
    public void pontoSemNumero() {
        onView(withId(R.id.btnVirgula)).perform(click());

        onView(withId(R.id.txtResultado)).check(matches(withText("")));
        onView(withId(R.id.txtSubResultado)).check(matches(withText("")));
    }

    @Test
    public void igualSemConta() {
        onView(withId(R.id.btnIgual)).perform(click());

        onView(withId(R.id.txtResultado)).check(matches(withText("")));
        onView(withId(R.id.txtSubResultado)).check(matches(withText("")));
    }

    @Test
    public void divisaoPorZero() {
        // testar se da pra fazer divisão por zero
        onView(withId(R.id.btn6)).perform(click());
        onView(withId(R.id.btnDivisao)).perform(click());
        onView(withId(R.id.btn0)).perform(click());
        onView(withId(R.id.btnIgual)).perform(click());

        onView(withId(R.id.txtResultado)).check(matches(withText("6.0")));
        onView(withId(R.id.txtSubResultado)).check(matches(withText("")));
    }

    @Test
    public void porcentagem() {
        onView(withId(R.id.btn1)).perform(click());
        onView(withId(R.id.btnPorcentagem)).perform(click());

        onView(withId(R.id.txtSubResultado)).check(matches(withText("0.01")));
        onView(withId(R.id.txtResultado)).check(matches(withText("")));
    }

    @Test
    public void trocaSinal() {
        onView(withId(R.id.btn1)).perform(click());
        onView(withId(R.id.btnSinal)).perform(click());

        onView(withId(R.id.txtSubResultado)).check(matches(withText("-1.0")));
        onView(withId(R.id.txtResultado)).check(matches(withText("")));
    }

    @Test
    public void trocaSinalSemNumero() {
        onView(withId(R.id.btnSinal)).perform(click());

        onView(withId(R.id.txtSubResultado)).check(matches(withText("")));
        onView(withId(R.id.txtResultado)).check(matches(withText("")));
    }

    @Test
    public void contasConsecutivas() {
        onView(withId(R.id.btn3)).perform(click());
        onView(withId(R.id.btnMais)).perform(click());
        onView(withId(R.id.btn4)).perform(click());

        onView(withId(R.id.txtResultado)).check(matches(withText("3.0 +")));
        onView(withId(R.id.txtSubResultado)).check(matches(withText("4")));

        // igual =
        onView(withId(R.id.btnIgual)).perform(click());

        onView(withId(R.id.txtResultado)).check(matches(withText("7.0")));
        onView(withId(R.id.txtSubResultado)).check(matches(withText("")));

        onView(withId(R.id.btn8)).perform(click());
        onView(withId(R.id.btnMultiplicacao)).perform(click());

        // conferir dados da tela
        onView(withId(R.id.txtResultado)).check(matches(withText("7.0 x")));
        onView(withId(R.id.txtSubResultado)).check(matches(withText("8")));

        // conferir resultado da conta
        onView(withId(R.id.btnIgual)).perform(click());
        onView(withId(R.id.txtResultado)).check(matches(withText("56.0")));
        onView(withId(R.id.txtSubResultado)).check(matches(withText("")));

        onView(withId(R.id.btn3)).perform(click());
        onView(withId(R.id.btnVirgula)).perform(click());
        onView(withId(R.id.btn4)).perform(click());
        onView(withId(R.id.btnMenos)).perform(click());

        // conferir dados da tela
        onView(withId(R.id.txtResultado)).check(matches(withText("56.0 -")));
        onView(withId(R.id.txtSubResultado)).check(matches(withText("3.4")));

        // conferir resultado da conta
        onView(withId(R.id.btnIgual)).perform(click());
        onView(withId(R.id.txtResultado)).check(matches(withText("52.6")));
    }


}
