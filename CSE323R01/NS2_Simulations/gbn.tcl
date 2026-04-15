set ns [new Simulator]

set nf [open gbn.nam w]
$ns namtrace-all $nf
set tf [open gbn.tr w]
$ns trace-all $tf

set n0 [$ns node]
set n1 [$ns node]

$ns duplex-link $n0 $n1 1Mb 10ms DropTail

# Go-Back-N (window size 5)
set tcp [new Agent/TCP]
$tcp set window_ 5
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
    puts "Go-Back-N simulation completed"
    exit 0
}

puts "Starting Go-Back-N Simulation..."
$ns run