set ns [new Simulator]

set tracefile [open dv.tr w]
$ns trace-all $tracefile
set namfile [open dv.nam w]
$ns namtrace-all $namfile

# Enable Distance Vector routing
$ns rtproto DV

# Create 4 nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

# Create links with costs (delays)
$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n0 $n2 1Mb 40ms DropTail
$ns duplex-link $n1 $n3 1Mb 20ms DropTail
$ns duplex-link $n2 $n3 1Mb 10ms DropTail

# TCP traffic from n0 to n3
set tcp [new Agent/TCP]
set sink [new Agent/TCPSink]
$ns attach-agent $n0 $tcp
$ns attach-agent $n3 $sink
$ns connect $tcp $sink

set ftp [new Application/FTP]
$ftp attach-agent $tcp

$ns at 1.0 "$ftp start"
$ns at 8.0 "$ftp stop"

# Link failure simulation
$ns rtmodel-at 4.5 down $n1 $n3
$ns rtmodel-at 7.0 up $n1 $n3

$ns at 9.0 "finish"

proc finish {} {
    global ns tracefile namfile
    $ns flush-trace
    close $tracefile
    close $namfile
    puts "DV Routing simulation completed"
    exit 0
}

puts "Starting Distance Vector Routing Simulation..."
$ns run