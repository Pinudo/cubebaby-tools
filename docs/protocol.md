# CubeBaby Protocol Specification

**Version:** 0.1 (Draft)

---

# Purpose

This document describes the communication protocol used by the M-VAVE Cube Baby family.

Its objective is to provide a complete technical reference for implementing open-source editors without relying on CubeSuite.

This specification is a living document and will evolve as new protocol details are discovered.

---

# Supported Devices

Current target devices:

- Cube Baby Guitar
- Cube Baby AC
- Cube Baby Bass

Future compatibility with other M-VAVE products is expected but not guaranteed.

---

# Communication Overview

Communication occurs through USB MIDI using MIDI System Exclusive (SysEx) packets.

```
Android

↓

USB Host API

↓

USB MIDI

↓

SysEx Transport

↓

Cube Baby
```

No proprietary USB protocol has been identified.

USB is used only as the transport layer.

---

# USB Identification

Current known values

| Property | Value |
|----------|-------|
| Vendor ID | 0x301A |
| Product ID | 0x5555 |

Known interfaces

| Interface | Description |
|-----------|-------------|
| USB Audio | Audio Streaming |
| USB MIDI | SysEx Communication |

---

# MIDI Transport

Transport type:

```
USB MIDI
```

Protocol:

```
MIDI System Exclusive
```

Packet delimiters

```
Start

F0

...

End

F7
```

---

# Packet Encoding

Cube Baby packets are not transmitted as raw bytes.

Payloads are encoded using MIDI-safe 7-bit packing.

General flow

```
Raw Packet

↓

7-bit Encoder

↓

SysEx

↓

USB MIDI
```

Incoming packets follow the reverse process.

```
USB MIDI

↓

SysEx

↓

7-bit Decoder

↓

Raw Packet
```

---

# Raw Packet Structure

Current understanding

```
+----------+
| Header   |
+----------+
| Type     |
+----------+
| Length   |
+----------+
| Payload  |
+----------+
|Checksum  |
+----------+
```

Known header bytes

```
00 59
```

---

# Checksum

Current checksum algorithm

```
checksum = NOT(sum(all packet bytes))
```

Rules

- 8-bit overflow
- One's complement
- Single checksum byte

Status

Verified by protocol reverse engineering.

---

# Message Types

Known message identifiers

| ID | Description | Status |
|----|-------------|--------|
| 0x21 | Flash Operation | Partial |
| 0x22 | Memory Write | Confirmed |
| 0x23 | Memory Read | Confirmed |

Additional message types remain unknown.

---

# Memory Model

Cube Baby behaves as a memory-mapped device.

Instead of high-level commands such as

```
SetAmp()

SetEQ()

SetCab()
```

The device expects read/write operations over memory addresses.

General model

```
Read(address)

↓

Bytes
```

```
Write(address, bytes)
```

---

# Memory Map

Status

UNKNOWN

Known regions

| Region | Status |
|---------|--------|
| Presets | Unknown |
| Amp | Unknown |
| Cabinet | Unknown |
| EQ | Unknown |
| Delay | Unknown |
| Reverb | Unknown |
| Compressor | Unknown |
| Noise Gate | Unknown |
| IR Slot | Unknown |

Future reverse engineering will populate this section.

---

# Presets

Current assumption

Presets are stored as contiguous memory blocks.

Workflow

```
Read Memory

↓

Preset Structure

↓

Modify

↓

Write Memory
```

---

# Impulse Responses

Known format

| Property | Value |
|----------|-------|
| Sample Rate | 48000 Hz |
| Channels | Mono |
| Format | Float32 |
| Length | 512 Samples |
| Size | 2048 Bytes |

Conversion utilities will be implemented by CubeBaby Tools.

---

# Protocol Layers

```
Application

↓

CubeBaby Core

↓

Packet Builder

↓

Encoder

↓

SysEx

↓

USB MIDI

↓

Cube Baby
```

---

# Planned API

Future library abstraction

```kotlin
CubeBaby.connect()

CubeBaby.readMemory()

CubeBaby.writeMemory()

CubeBaby.getFirmware()

CubeBaby.uploadIR()
```

Applications should never manipulate raw SysEx packets directly.

---

# Current Project Status

## Confirmed

- USB MIDI transport
- SysEx communication
- 7-bit encoding
- Packet checksum
- Memory read/write model
- IR format

---

## Under Investigation

- Firmware query
- Device identification
- Preset format
- Memory map
- IR upload sequence
- Status responses
- Error codes

---

# Reverse Engineering Methodology

Every protocol discovery must satisfy at least one of the following:

- Captured from CubeSuite communication
- Validated experimentally
- Reproduced independently

Hypotheses must always be marked as such until confirmed.

---

# Development Principles

CubeBaby Tools follows three principles.

## Documentation First

No protocol feature should be implemented before being documented.

---

## Platform Independence

Business logic must never depend on Android APIs.

Protocol code belongs exclusively inside **cubebaby-core**.

---

## Android First

Although the architecture is cross-platform, Android is the primary supported platform.

All new features must be validated on Android before being considered complete.

---

# References

Current protocol knowledge is based on:

- Public reverse engineering research
- MIDI protocol analysis
- USB inspection
- Community experimentation

Additional references will be added as the project evolves.

---

# Changelog

## Version 0.1

Initial protocol specification.
