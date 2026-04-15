set ns [new Simulator]

set nf [open leaky.nam w]
$ns namtrace-all $nf
set tf [open leaky.tr w]
$ns trace-all $tf

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 1Mb 10ms DropTail

# Set queue limit (Bucket capacity)
$ns queue-limit $n1 $n2 10

set udp [new Agent/UDP]
$ns attach-agent $n0 $udp

set null [new Agent/Null]
$ns attach-agent $n2 $null
$ns connect $udp $null

set cbr [new Application/Traffic/CBR]
$cbr set packetSize_ 500
$cbr set interval_ 0.01
$cbr attach-agent $udp

$ns at 0.5 "$cbr start"
$ns at 5.0 "finish"

proc finish {} {
    global ns tf nf
    $ns flush-trace
    close $tf
    close $nf
    puts "Leaky Bucket simulation completed"
    exit 0
}

$ns run