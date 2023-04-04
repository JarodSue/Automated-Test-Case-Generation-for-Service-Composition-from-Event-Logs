The application is located in the directory AlgoByservices
The logs used are in the directory AllLogs

In order to use the application, you need to have event files representing one session each and a regex using all this groups :

<date> for the timestamp
<label> for the protocol used
<status> for the status when it's a response
<verb> for the verb used when it's a request
<path> for the path to the service tested when it's a request
<srcPort> for the source port/address
<dstPort> for the destination port/address
<body> for the body of the request/response

example of a regex possible with logs located inside AllLogs/service2/logsSession1.txt captured with wireshark
"(?<date>\d*.\d*"),\S{1}(?<label>(.*?)(?="))","(?<status>(.*?)(?="))","(?<verb>(.*?)(?="))","(?<path>(.*?)(?="))","(?<srcPort>(.*?)(?="))","(?<dstPort>(.*?)(?="))","(?<body>(.*?)(?="))"
