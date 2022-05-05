# RISC-V_Simulator

An attempt to make a RISC V Simulator using Java
And further extending it to Graphical User interphase

---
Printing the memory and registers

![Memory](https://github.com/Samveg-techie/RISC-V_Simulator/blob/master/RISC-V_Simulator-main/Static/memPrint.png)

Processed the input file

![Input](https://github.com/Samveg-techie/RISC-V_Simulator/blob/master/RISC-V_Simulator-main/Static/input.png)

Detected control flow

![Control Flow](https://github.com/Samveg-techie/RISC-V_Simulator/blob/master/RISC-V_Simulator-main/Static/controlFlow.png)

Detection of dependencies and stalls

![Stall Detection](https://github.com/Samveg-techie/RISC-V_Simulator/blob/master/RISC-V_Simulator-main/Static/stall1.png)

![Stall Detection](https://github.com/Samveg-techie/RISC-V_Simulator/blob/master/RISC-V_Simulator-main/Static/stall2.png)


---

\
Progress till now:

    - Our RISC V Simulator is terminal based java program that can take external RISC program and show its step=by-step control flow
    - Idea of pipeline has been implemented
    - Pipeline is perfectly working for operations like add/addi/sub/subi, etc. load word, and most of the jump statements and branching statements
    - User has the option to enable or disable the data forwarding
    - Depending on user's choise, stalls will be calculated
    - Stalls are calculated based on the dependencies from the previously executed instructions
    - Each dependency detected is labelled in the console as "DEPENDENCY DETECTED"
    - Along with that, we will print "STALL DETECTED". And the instruction for which stall is detected
    -User can also see the previous as well as the previous-to-previous instruction for the stall (for better understanding)
    - At the end, the total number of stalls are also printed
    - Depending on whether data forwarding is enabled or disabled, the stall will be calculated
    - At the end, we are printing the list of registers and non-zero memory values
    - Another good feature available in our simulator is that it displays the line-by-line step of the control flow. This helps the user to better understand the code

\
Unfinished plans:

    - We wanted to develop basic GUI to make it user friendly
    - Develop a basic branch predictor which decides the control flow basesd on previous instructions

Next to implement:

    - Make the GUI for the RISC simulator using 'Swing' in JAVA
    - Show a 2D table containing all the instructions and their 5 stages
    - File upload option in the GUI for ease of access
    - We also plan to make a branch predictor which can dynamically change the control flow based on the history

---

```
Team RISCy_Coders

CS20B034 SACHIN KUMAR SAHU

CS20B039 SAMVEG SHAH
```
