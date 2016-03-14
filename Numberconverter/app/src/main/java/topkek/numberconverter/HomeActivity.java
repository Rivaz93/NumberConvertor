package topkek.numberconverter;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.root)
    protected RelativeLayout mRoot;
    @Bind(R.id.number_input)
    protected EditText mInputNumber;
    @Bind(R.id.number_output_eng)
    protected EditText mOutputNumberEng;
    @Bind(R.id.number_output_ro)
    protected EditText mOutputNumberRo;
    protected long mNumber;

    private final String[] units = {
            "Zero",
            "One",
            "Two",
            "Three",
            "Four",
            "Five",
            "Six",
            "Seven",
            "Eight",
            "Nine",
            "Ten",
            "Eleven",
            "Twelve",
            "Thirteen",
            "Fourteen",
            "Fifteen",
            "Sixteen",
            "Seventeen",
            "Eighteen",
            "Nineteen"};

    private final String[] tens = {
            "",
            "",
            "Twenty",
            "Thirty",
            "Forty",
            "Fifty",
            "Sixty",
            "Seventy",
            "Eighty",
            "Ninety"
    };

    private final String[] unitati = {
            "Zero",
            "Unu",
            "Doi",
            "Trei",
            "Patru",
            "Cinci",
            "Sase",
            "Sapte",
            "Opt",
            "Noua",
            "Zece",
            "Unsprezece",
            "Doisprezece",
            "Treisprezece",
            "Paisprezece",
            "Cinsprezece",
            "Saisprezece",
            "Saptesprezece",
            "Optsprezece",
            "Nouasprezece"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mOutputNumberEng.setKeyListener(null);
        mOutputNumberEng.setFocusableInTouchMode(false);
        mOutputNumberRo.setKeyListener(null);
        mOutputNumberRo.setFocusableInTouchMode(false);
        mInputNumber.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    mNumber = Long.parseLong(mInputNumber.getText().toString());
                    if (mNumber > 999999999999.0) {
                        Snackbar.make(mRoot, "Number is too large!\n0 and 999 999 999 999", Snackbar.LENGTH_LONG)
                                .show();
                    } else {
                        mOutputNumberEng.setText(numToWordEng(mNumber) + "");
                        mOutputNumberRo.setText(numToWordRo(mNumber) + "");
                    }
                    return true;
                }
                return false;
            }
        });
    }

    //English
    public String numToWordEng(long number) {

        if (number < 20) {
            return units[((int) number)];
        }
        if (number < 100) {
            if (number % 10 > 0) {
                return tens[((int) (number / 10))] + " " + numToWordEng(number % 10);
            } else {
                return tens[((int) (number / 10))];
            }
        }
        if (number < 1000) {
            if (number % 100 > 0) {
                return units[((int) (number / 100))] + " Hundred" + " " + numToWordEng(number % 100);
            } else {
                return units[((int) (number / 100))] + " Hundred";
            }
        }
        if (number < 1000000) {
            if (number % 1000 > 0) {
                return numToWordEng(number / 1000) + " Thousand " + numToWordEng(number % 1000);
            } else {
                return numToWordEng(number / 1000) + " Thousand";
            }
        }
        if (number < 1000000000) {
            if (number % 1000000 > 0) {
                return numToWordEng(number / 1000000) + " Million " + numToWordEng(number % 1000000);
            } else {
                return numToWordEng(number / 1000000) + " Million";
            }
        }
        if (number % 1000000000 > 0) {
            return numToWordEng(number / 1000000000) + " Billion " + numToWordEng(number % 1000000000);
        } else {
            return numToWordEng(number / 1000000000) + " Billion";
        }
    }

    //Romanian
    public String numToWordRo(long number) {

        if (number < 20) {
            return unitati[((int) number)];
        }
        if (number < 100) {
            if (number % 10 > 0) {
                if ((number / 10) % 100 == 2) {
                    return "Doua Zeci Si " + numToWordRo(number % 10);
                } else if ((number / 10) % 100 == 6) {
                    return "Saizeci Si " + numToWordRo(number % 10);
                } else {
                    return unitati[((int) (number / 10))] + " Zeci Si " + numToWordRo(number % 10);
                }
            } else {
                if ((number / 10) % 100 == 2) {
                    return "Doua Zeci";
                } else if ((number / 10) % 100 == 6) {
                    return "Saizeci";
                } else {
                    return unitati[((int) (number / 10))] + " Zeci";
                }
            }
        }
        if (number < 1000) {
            if (number % 100 > 0) {
                if ((number / 100) % 10 == 1) {
                    return "O Suta " + numToWordRo(number % 100);
                } else if ((number / 100) % 10 == 2) {
                    return "Doua Sute " + numToWordRo(number % 100);
                } else {
                    return unitati[((int) (number / 100))] + " Sute " + numToWordRo(number % 100);
                }
            } else {
                if ((number / 100) % 10 == 1) {
                    return "O Suta";
                } else if ((number / 100) % 10 == 2) {
                    return "Doua Sute";
                } else {
                    return unitati[((int) (number / 100))] + " Sute";
                }
            }
        }
        if (number < 10000) {
            if (number % 1000 > 0) {
                if ((number / 1000) % 10 == 1 && number < 2000) {
                    return "O Mie " + numToWordRo(number % 1000);
                } else if ((number / 1000) % 10 == 2 && number < 3000) {
                    return "Doua Mii " + numToWordRo(number % 1000);
                } else {
                    return numToWordRo(number / 1000) + " Mii " + numToWordRo(number % 1000);
                }
            } else {
                if ((number / 1000) % 10 == 1) {
                    return "O Mie ";
                } else if ((number / 1000) % 10 == 2) {
                    return "Doua Mii ";
                } else {
                    return numToWordRo(number / 1000) + " Mii";
                }
            }
        }

        if (number < 1000000) {
            if (number % 1000 > 0) {
                if (number < 19999) {
                    return numToWordRo(number / 1000) + " Mii " + numToWordRo(number % 1000);
                } else {
                    return numToWordRo(number / 1000) + " De Mii " + numToWordRo(number % 1000);
                }
            } else {
                if (number < 19999) {
                    return numToWordRo(number / 1000) + " Mii";
                } else {
                    return numToWordRo(number / 1000) + " De Mii";
                }
            }
        }

        if (number < 1000000000) {
            if (number % 1000000 > 0) {
                if (number <= 1999999) {
                    return "Un Milion " + numToWordRo(number % 1000000);
                } else if (number <= 2999999) {
                    return "Doua Milioane " + numToWordRo(number % 1000000);
                } else if (number < 20000000) {
                    return numToWordRo(number / 1000000) + " Milioane " + numToWordRo(number % 1000000);
                } else {
                    return numToWordRo(number / 1000000) + " De Milioane " + numToWordRo(number % 1000000);
                }
            } else {
                if (number == 1000000) {
                    return "Un Milion";
                } else if (number == 2000000) {
                    return "Doua Milioane";
                } else if (number < 20000000) {
                    return numToWordRo(number / 1000000) + " Milioane";
                } else {
                    return numToWordRo(number / 1000000) + " De Milioane";
                }
            }
        }

        if (number % 1000000000 > 0) {
            if (number < 20000000000.0) {
                if (number > 1000000000 && number <= 1999999999) {
                    return "Un Miliard " + numToWordRo(number % 1000000000);
                } else if (number > 2000000000 && number <= 2999999999.0) {
                    return "Doua Miliarde " + numToWordRo(number % 1000000000);
                } else {
                    return numToWordRo(number / 1000000000) + " Miliarde " + numToWordRo(number % 1000000000);
                }
            } else {
                return numToWordRo(number / 1000000000) + " De Miliarde " + numToWordRo(number % 1000000000);
            }
        } else {
            if (number == 1000000000.0) {
                return "Un Miliard";
            } else if (number == 2000000000) {
                return "Doua Miliarde";
            } else {
                if (number < 20000000000.0) {
                    return numToWordRo(number / 1000000000) + " Miliarde";
                } else {
                    return numToWordRo(number / 1000000000) + " De Miliarde";
                }
            }
        }
    }
}
