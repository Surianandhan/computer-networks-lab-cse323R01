set ns [new Simulator]

# Enable Link State routing
$ns rtproto LS

set tracefile [open ls.tr w]
$ns trace-all $tracefile
set namfile [open ls.nam w]
$ns namtrace-all $namfile

# Create 5 nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]

# Create links
$ns duplex-link $n0 $n1 2Mb 10ms DropTail
$ns duplex-link $n0 $n2 2Mb 25ms DropTail
$ns duplex-link $n1 $n3 2Mb 15ms DropTail
$ns duplex-link $n2 $n3 2Mb 20ms DropTail
$ns duplex-link $n1 $n4 2Mb 5ms DropTail
$ns duplex-link $n4 $n3 2Mb 12ms DropTail

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

# CBR background traffic
set udp [new Agent/UDP]
set null [new Agent/Null]
$ns attach-agent $n1 $udp
$ns attach-agent $n2 $null
$ns connect $udp $null

set cbr [new Application/Traffic/CBR]
$cbr attach-agent $udp
$ns at 2.0 "$cbr start"

# Link failure
$ns rtmodel-at 4.0 down $n1 $n3
$ns rtmodel-at 7.0 up $n1 $n3

$ns at 9.0 "finish"

proc finish {} {
    global ns tracefile namfile
    $ns flush-trace
    close $tracefile
    close $namfile
    puts "Link State Routing simulation completed"
    exit 0
}

puts "Starting Link State Routing Simulation..."
$ns run