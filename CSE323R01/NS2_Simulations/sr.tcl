set ns [new Simulator]

set nf [open sr.nam w]
$ns namtrace-all $nf
set tf [open sr.tr w]
$ns trace-all $tf

set n0 [$ns node]
set n1 [$ns node]

$ns duplex-link $n0 $n1 1Mb 10ms DropTail

# Selective Repeat (TCP/Sack1)
set tcp [new Agent/TCP/Sack1]
$tcp set window_ 5
$tcp set packetSize_ 1000
$ns attach-agent $n0 $tcp

set sink [new Agent/TCP/Sink/Sack1]
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
    puts "Selective Repeat simulation completed"
    exit 0
}

puts "Starting Selective Repeat Simulation..."
$ns run