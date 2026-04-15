set ns [new Simulator]

set nf [open stopwait.nam w]
$ns namtrace-all $nf
set tf [open stopwait.tr w]
$ns trace-all $tf

set n0 [$ns node]
set n1 [$ns node]

$ns duplex-link $n0 $n1 1Mb 10ms DropTail

# Stop-and-Wait (window size 1)
set tcp [new Agent/TCP]
$tcp set window_ 1
$tcp set packetSize_ 1000
$ns attach-agent $n0 $tcp

set sink [new Agent/TCPSink]
$ns attach-agent $n1 $sink
$ns connect $tcp $sink

set ftp [new Application/FTP]
$ftp attach-agent $tcp

$ns at 0.5 "$ftp start"
$ns at 4.5 "$ftp stop"
$ns at 5.0 "finish"

proc finish {} {
    global ns nf tf
    $ns flush-trace
    close $nf
    close $tf
    puts "Stop-and-Wait simulation completed"
    exit 0
}

puts "Starting Stop-and-Wait Simulation..."
$ns run