set ns [new Simulator]

set tf [open token.tr w]
$ns trace-all $tf
set nf [open token.nam w]
$ns namtrace-all $nf

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 1Mb 10ms DropTail

# Set queue limit (Token bucket capacity)
$ns queue-limit $n1 $n2 15

set udp [new Agent/UDP]
$ns attach-agent $n0 $udp

set null [new Agent/Null]
$ns attach-agent $n2 $null
$ns connect $udp $null

# Exponential ON/OFF traffic for burstiness
set expo [new Application/Traffic/Exponential]
$expo set packetSize_ 500
$expo set burst_time_ 0.5
$expo set idle_time_ 0.2
$expo set rate_ 1Mb
$expo attach-agent $udp

$ns at 0.5 "$expo start"
$ns at 4.5 "$expo stop"
$ns at 5.0 "finish"

proc finish {} {
    global ns tf nf
    $ns flush-trace
    close $tf
    close $nf
    puts "Token Bucket simulation completed"
    exit 0
}

$ns run