//  Name: Daniel Brackin
//  Date: Feb 6 2016
//  Filename: hw3.cpp
//  Auburn UserID: DLB0031
//  Development Environment: xCode, g++
//  Sources used: pressanykey.cpp


# include <iostream>
# include <stdlib.h>
# include <assert.h>
# include <ctime>
# include <cstdlib>
# include <termios.h>
# include <stdio.h>
# include <unistd.h>
# include <ctype.h>

using namespace std;
double shot = 0.0;
const int AARON = 33;
const int BOB = 50;
const int CHARLIE = 100;
const int MAX_SIMU_NUM = 10000;

bool a_alive = true;
bool b_alive = true;
bool c_alive = true;

// Initializing functions
void test_at_least_two_alive(void);
bool at_least_two_alive(bool a_alive,bool b_alive,bool c_alive);

void test_a_shoots1(void);
void Aaron_shoots1 (bool&, bool&);

void test_b_shoots(void);
void Bob_shoots (bool&, bool&);

void test_c_shoots(void);
void Charlie_shoots (bool&, bool&);

void test_a_shoots2(void);
void Aaron_shoots2 (bool&, bool&);

int mygetch(void);
void press_any_key(void);
//Aaron shot 1/3; Bob shot 1/2; Charlie shot 1;

int main() {
    srand(time(0));
    int numberofruns = 10000;
    double a_win1 = 0.0;
    double b_win = 0.0;
    double c_win = 0.0;
    double a_win2 = 0.0;
    
    // STARTING SIMULATOR
    cout << "*** Welcome to Daniel Brackin's Duel Simulator ***\n";
    
    // Unit Testing 1
    test_at_least_two_alive();
    press_any_key();
    
    // Unit Testing 2
    test_a_shoots1();
    press_any_key();

    // Unit Testing 3
    test_b_shoots();
    press_any_key();
    
    // Unit Testing 4
    test_c_shoots();
    press_any_key();
    
    // Unit Testing 5
    test_a_shoots2();
    press_any_key();
    
    cout << "\nReady to test strategy 1 (run 10000 times):\n";
    press_any_key();
    
    // Strategy 1

    for (int i = 0; i < MAX_SIMU_NUM; i++) {
        a_alive = true;
        b_alive = true;
        c_alive = true;
        while (at_least_two_alive(a_alive, b_alive, c_alive)) {
        
        if (a_alive) {
            Aaron_shoots1(b_alive, c_alive);
            cout << endl;
        }
        if (b_alive) {
            Bob_shoots(a_alive, c_alive);
            cout << endl;
        }
        if (c_alive) {
            Charlie_shoots(a_alive, b_alive);
            cout << endl;
            }
        }
    
        if (a_alive && b_alive == false && c_alive ==false) {
            a_win1++;
        }
        if (b_alive && a_alive == false && c_alive ==false) {
            b_win++;
        }
        if (c_alive && a_alive == false && b_alive ==false) {
            c_win++;
        }
    }
    
// Results for Strategy 1
        
        cout << "\nAaron won " << a_win1 << "/10000 duels or " << a_win1/100 << "%" << endl;
        cout << "Bob won " << b_win << "/10000 duels or " << b_win/100 << "%" << endl;
        cout << "Charlie won " << c_win << "/10000 duels or " << c_win/100 << "%" << endl;
    
    
// Strategy 2
    
    b_win = 0.0;
    c_win = 0.0;
    
    
    cout << "\nReady to test strategy 2 (run 10000 times):\n";
    press_any_key();
    
    
    for (int i = 0; i < MAX_SIMU_NUM; i++) {
        a_alive = true;
        b_alive = true;
        c_alive = true;
        while (at_least_two_alive(a_alive, b_alive, c_alive)) {
            
            if (a_alive) {
                Aaron_shoots2(b_alive, c_alive);
                cout << endl;
            }
            if (b_alive) {
                Bob_shoots(a_alive, c_alive);
                cout << endl;
            }
            if (c_alive) {
                Charlie_shoots(a_alive, b_alive);
                cout << endl;
            }
        }
        if (a_alive && b_alive == false && c_alive ==false) {
            a_win2++;
        }
        if (b_alive && a_alive == false && c_alive ==false) {
            b_win++;
        }
        if (c_alive && a_alive == false && b_alive ==false) {
            c_win++;
        }
    }
    
// Results for Strategy 2
    
    cout << "\nAaron won " << a_win2 << "/10000 duels or " << a_win2/100 << "%" << endl;
    cout << "Bob won " << b_win << "/10000 duels or " << b_win/100 << "%" << endl;
    cout << "Charlie won " << c_win << "/10000 duels or " << c_win/100 << "%" << endl;
    
    if (a_win2 > a_win1) {
        cout << "\nStrategy 2 is better than Strategy 1\n";
    }
    if (a_win2 < a_win1) {
        cout << "\nStrategy 1 is better than Strategy 2\n";
    }
    
}

// AT LEAST TWO ALIVE FUNCTION

bool at_least_two_alive(bool a_alive, bool b_alive, bool c_alive) {
    
    if ((a_alive && b_alive) ||
        (b_alive && c_alive) ||
        (c_alive && a_alive)) {
        return true;
    }
    else {
        return false;
    }
}

// AARON SHOOTS

void Aaron_shoots1 (bool& b_alive, bool& c_alive) {
    
    shot = rand() % 100;
    if (c_alive == true) { // Charlie is alive
            if (shot <= AARON) {
            cout << "Charlie is dead";
            c_alive = false;
        }
        else {
            cout << "Aaron misses";
        }
    }
        else {
            if (b_alive == true) // Bob is alive
                if (shot < AARON) {
                    cout << "Bob is dead";
                    b_alive = false;
                    }
            else {
                cout << "Aaron misses";
            }
        
    }

}

// BOB SHOOTS
    
    void Bob_shoots (bool& a_alive, bool& c_alive) {
       
        shot = rand() % 100;
        if (c_alive == true) { // Charlie is alive
            if (shot < BOB) {
                cout << "Charlie is dead";
                c_alive = false;
            }
            else {
                cout << "Bob misses";
            }
        }
        else {
            if (a_alive == true) // Aaron is alive
                if (shot < BOB) {
                    cout << "Aaron is dead";
                    a_alive = false;
                }
                else {
                    cout << "Bob misses";
                }
        }
    }
   
        
// CHARLIE SHOOTS

    void Charlie_shoots (bool& a_alive, bool& b_alive) {
       
        shot = rand() % 100;
        if (b_alive == true) { // Charlie is alive
            if (shot < CHARLIE) {
                cout << "Charlie is dead";
                b_alive = false;
            }
            else {
                cout << "Charlie misses";
            }
        }
        else {
            if (a_alive == true) // Aaron is alive
                if (shot < CHARLIE) {
                    cout << "Aaron is dead";
                    a_alive = false;
                }
                else {
                    cout << "Charlie misses";
                }
        }
    }

// AARON SHOOTS

    void Aaron_shoots2 (bool& b_alive, bool& c_alive) {
        if (b_alive == true && c_alive == true) {
            cout << "Both Bob and Charlie are alive";
        }
        else {
            Aaron_shoots1(b_alive, c_alive);
        }
    }



// TEST FOR AT LEAST TWO ALIVE
    void test_at_least_two_alive(void) {
        
        cout << "Unit Testing 1: Function - at_least_two_alive()\n";
        
        cout << "Case 1: Aaron, Bob, Charlie all alive \n";
        assert(true == at_least_two_alive(true, true, true));
        cout << "Case passed...\n";
        
        cout << "Case 2: Aaron dead, Bob alive, Charlie alive \n";
        assert(true == at_least_two_alive(false, true, true));
        cout << "Case passed...\n";
        
        cout << "Case 3: Aaron alive, Bob dead, Charlie alive \n";
        assert(true == at_least_two_alive(true, false, true));
        cout << "Case passed...\n";
        
        cout << "Case 4: Aaron alive, Bob alive, Charlie dead \n";
        assert(true == at_least_two_alive(true, true, false));
        cout << "Case passed...\n";
        
        cout << "Case 5: Aaron dead, Bob dead, Charlie alive \n";
        assert(false == at_least_two_alive(false, false, true));
        cout << "Case passed...\n";
        
        cout << "Case 6: Aaron dead, Bob alive, Charlie dead \n";
        assert(false == at_least_two_alive(false, true, false));
        cout << "Case passed...\n";
        
        cout << "Case 7: Aaron alive, Bob dead, Charlie dead \n";
        assert(false == at_least_two_alive(true, false, false));
        cout << "Case passed...\n";
        
        cout << "Case 8: Aaron, Bob, and Charlie are all dead \n";
        assert(false == at_least_two_alive(false, false, false));
        cout << "Case passed...\n";
}

// TEST FOR AARON SHOOTS 1

void test_a_shoots1(void) {
    bool b_alive = true, c_alive = true;
    
    cout << "\nUnit Testing 2: Function Aaron_shoots1(Bob_alive, Charlie_alive)\n";
  
    // Case 1:
    cout << "Case 1: Bob alive, Charlie alive\n\t\tAaron is shooting at Charlie \n\t\t";
    Aaron_shoots1(b_alive, c_alive);
    
    // Case 2:
    b_alive = true;
    c_alive = true;
    cout << "\nCase 2: Bob dead, Charlie alive\n\t\tAaron is shooting at Charlie \n\t\t";
    Aaron_shoots1(b_alive, c_alive);
    
    // Case 3:
    b_alive = true;
    c_alive = false;
    cout << "\nCase 3: Bob alive, Charlie dead\n\t\tAaron is shooting at Bob \n\t\t";
    Aaron_shoots1(b_alive, c_alive);
    cout << "\n";
}

// TEST FOR BOB SHOOTS

void test_b_shoots(void) {
    bool a_alive = true, c_alive = true;
    
    cout << "\nUnit Testing 3: Function Bob_shoots(Aaron_alive, Charlie_alive)\n";
    
    // Case 1:
    cout << "Case 1: Aaron alive, Charlie alive\n\t\tBob is shooting at Charlie \n\t\t";
    Bob_shoots(a_alive, c_alive);
    
    // Case 2:
    a_alive = false;
    c_alive = true;
    cout << "\nCase 2: Aaron dead, Charlie alive\n\t\tAaron is shooting at Charlie \n\t\t";
    Bob_shoots(a_alive, c_alive);
    
    // Case 3:
    a_alive = true;
    c_alive = false;
    cout << "\nCase 3: Aaron alive, Charlie dead\n\t\tBob is shooting at Aaron \n\t\t";
    Bob_shoots(a_alive, c_alive);
    cout << "\n";
}

// TEST FOR CHARLIE SHOOTS 1


void test_c_shoots(void) {
    
    bool a_alive = true, b_alive = true;
    
    cout << "\nUnit Testing 4: Function Charlie_shoots(Aaron_alive, Bob_alive)\n";
    
    // Case 1:
    cout << "Case 1: Aaron alive, Bob alive\n\t\tCharlie is shooting at Bob \n\t\t";
    Charlie_shoots(a_alive, b_alive);
    
    // Case 2:
    a_alive = false;
    b_alive = true;
    cout << "\nCase 2: Aaron dead, Bob alive\n\t\tCharlie is shooting at Bob \n\t\t";
    Charlie_shoots(a_alive, b_alive);
    
    // Case 3:
    a_alive = true;
    b_alive = false;
    cout << "\nCase 3: Aaron alive, Bob dead\n\t\tCharlie is shooting at Aaron \n\t\t";
    Charlie_shoots(a_alive, b_alive);
    cout << "\n";
}



// TEST FOR AARON SHOOTS 2


void test_a_shoots2(void) {
    bool b_alive = true, c_alive = true;
    
    cout << "\nUnit Testing 5: Function Aaron_shoots2(Bob_alive, Charlie_alive)\n";
    
    // Case 1:
    cout << "Case 1: Bob alive, Charlie alive\n\t\tAaron intentionally misses his first shot \n\t\t";
    Aaron_shoots2(b_alive, c_alive);
    
    // Case 2:
    b_alive = false;
    c_alive = true;
    cout << "\nCase 2: Bob dead, Charlie alive\n\t\tAaron is shooting at Charlie \n\t\t";
    Aaron_shoots2(b_alive, c_alive);
    
    // Case 3:
    b_alive = true;
    c_alive = false;
    cout << "\nCase 3: Bob alive, Charlie dead\n\t\tAaron is shooting at Bob \n\t\t";
    Aaron_shoots2(b_alive, c_alive);
    cout << "\n";
}


// Source code from press_any_key.cpp //

void press_any_key(void) {
    char ch;
    cout << "Press any key to continue...";
    ch = mygetch();
    if (ch == 0 || ch == 224) mygetch();
}

int mygetch ( void )
{
    int ch;
    struct termios oldt, newt;
    tcgetattr ( STDIN_FILENO, &oldt );
    newt = oldt;
    newt.c_lflag &= ~( ICANON | ECHO );
    tcsetattr ( STDIN_FILENO, TCSANOW, &newt );
    ch = getchar();
    tcsetattr ( STDIN_FILENO, TCSANOW, &oldt );
    return ch;
}






















 
