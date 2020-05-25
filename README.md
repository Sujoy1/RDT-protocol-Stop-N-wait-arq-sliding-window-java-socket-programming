# RDT-protocol-Stop-N-wait-arq-sliding-window-java-socket-programming
A RDT protocol oriented Stop N Wait ARQ technique using Sliding Window protocol in Java Socket Programming :
 Brief descrition :
 CS602 Final project in Computer Networks under Prof. Ee Kian Wong to overcome problems in simple server-client data transfer and incorporate advance concepts like payload, sequencing and implement flow control of network simulations in Cisco PacketTracer. For more info, please visit : 
https://sites.google.com/a/iemcal.com/cs602-computernetworks/project2020

Adapted from course website
/*
In this project, you will implement in Java a Reliable Data Transfer protocol (RDT).  RDT provides reliability and flow control using the Automatic Repeat Request (ARQ) and Sliding Window protocols.
RDT is supposed to be implemented in the OS Kernel at the Data Link Layer.

However, that being too complex, let us implement RDT as a transport-layer File Transfer service over UDP, using the Sliding Window concepts we have learnt in the context of the Data Link Layer. 

[adapted from http://nsl.cs.sfu.ca/teaching/09/371/prj3_reliableTransferProtocol.html]

Objective
To implement a File Transfer service in Java using ARQ Protocols:
Stop-and-Wait
Go-Back-N
Selective Repeat
/*
