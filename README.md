Airport Check-in Queue Management System
A Java Swing based project to manage airport passenger check-in using a Queue data structure (FIFO), designed for academic and practical learning.

✈️ Features
Complete GUI: Java Swing based, user-friendly interface

Queue Implementation: Array-based circular queue

Passenger CRUD Operations: Add (ENQUEUE), Remove (DEQUEUE), View/Peek, Update, Search, Display All, Clear, Size

Validation: CNIC (13 digits), phone (11 digits), all fields required

Real Airlines and Destinations: PIA, AirBlue, SereneAir; Dubai, London, Toronto and more

Live Statistics & Logging: Real-time dashboard for queue status and reporting

⚙️ How to Run
Project open karo IntelliJ IDEA me ya koi bhi Java IDE me

Main class: gui.WelcomePage

Run karo aur GUI ka istemal karke sare operation test karo

🧑‍💻 Main Operations Explained
ENQUEUE: Passenger register karna aur queue me add karna

DEQUEUE: Front se pehla passenger process karna (remove)

PEEK: Sirf front passenger dekhna, bina remove kiye

UPDATE: Kisi passenger ka phone number update karna

SEARCH: Passenger name se search karna

DISPLAY: Puri queue table dekhna

CLEAR: Queue puri khaali kar dena

SIZE: Abhi kitne passenger hain, count dikhana

💼 Folder Structure
text
AirportCheckInQueue/
├── src/
│   ├── datastructure/
│   │    └── PassengerQueue.java
│   ├── gui/
│   │    ├── WelcomePage.java
│   │    ├── Dashboard.java
│   │    ├── AddPassengerWindow.java
│   │    ├── StatisticsWindow.java
│   │    └── MainFrame.java
│   └── models/
│        └── Passenger.java
├── .idea/
├── AirportCheckInQueue.iml
└── ... (baqi IntelliJ/Java files)
📋 How to Use CRUD Operations
Add Passenger: AddPassengerWindow form se details bhar ke queue me add karo

Queue Operations: MainFrame window me ENQUEUE, DEQUEUE, PEEK, UPDATE, SEARCH, DISPLAY, CLEAR, SIZE sab buttons ka istemal

Statistics: StatisticsWindow open karo aur queue ke stats/report check karo

🙋‍♂️ Developer
Roman Ali Maitloo

Data Structure Project (Java, Swing, Queue)
