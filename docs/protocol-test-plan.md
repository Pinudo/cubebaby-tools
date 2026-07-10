# CubeBaby Protocol Test Plan

**Version:** 0.1

---

# Purpose

This document defines the official reverse engineering methodology used by CubeBaby Tools.

The objective is to discover undocumented protocol behavior while keeping every experiment reproducible.

No assumptions should become part of the protocol unless experimentally validated.

---

# Test Environment

## Hardware

- M-VAVE Cube Baby AC
- USB OTG connection
- Android phone with USB Host support

---

## Software

Future tools

- CubeBaby CLI
- CubeBaby Android
- USB Device Info
- MIDI Monitor

---

# Validation Levels

Every discovery must be classified.

## Level 0

Hypothesis

No experimental evidence.

---

## Level 1

Observed

Observed during protocol capture.

Not reproduced.

---

## Level 2

Reproduced

Successfully reproduced using CubeBaby Tools.

---

## Level 3

Verified

Reproduced multiple times.

No inconsistent behavior.

---

# Test Categories

## Device Discovery

Goal

Confirm device detection.

Expected result

- USB device detected
- VID matches
- PID matches
- MIDI interface available

---

## MIDI Communication

Goal

Open MIDI communication.

Expected result

- Connection established
- No transport errors

---

## SysEx Communication

Goal

Transmit custom SysEx packets.

Expected result

Device responds.

---

## Packet Validation

Goal

Validate encoder and decoder.

Expected result

Decoded packet equals original packet.

---

## Checksum Validation

Goal

Validate checksum implementation.

Expected result

Accepted by device.

---

## Memory Read

Goal

Read arbitrary memory.

Unknowns

- Address range
- Alignment
- Maximum packet size

Expected result

Receive payload.

---

## Memory Write

Goal

Write arbitrary memory.

Expected result

Device accepts packet.

---

## Firmware Query

Goal

Discover firmware command.

Expected result

Receive firmware version.

---

## Preset Discovery

Goal

Locate preset memory.

Method

- Read candidate regions.
- Compare data after preset changes.

Expected result

Memory differences isolated.

---

## Parameter Discovery

Goal

Locate parameter offsets.

Candidate parameters

- Amp
- Cabinet
- Gain
- Bass
- Mid
- Treble
- Volume
- Delay
- Reverb
- Compressor
- Noise Gate

Method

Change one parameter at a time.

Compare memory dumps.

---

## IR Discovery

Goal

Locate IR memory.

Method

Upload different IRs.

Compare memory before and after.

Expected result

IR storage region identified.

---

# General Rules

Only change one variable per experiment.

Never change multiple pedal settings simultaneously.

Always record:

- Command sent
- Raw SysEx
- Decoded packet
- Device response
- Timestamp
- Firmware version

---

# Logging Format

Example

```
Test ID

T-001

Description

Firmware Query

Packet

F0 ...

Decoded

...

Response

...

Status

PASS
```

---

# Test IDs

Communication

COM-001

COM-002

COM-003

Memory

MEM-001

MEM-002

MEM-003

Firmware

FW-001

FW-002

Preset

PRE-001

PRE-002

IR

IR-001

IR-002

IR-003

---

# Success Criteria

A protocol feature is considered implemented only when:

- documented
- reproducible
- implemented
- tested on Android

---

# Future Automation

Every protocol test should eventually become an automated test inside CubeBaby CLI.

Manual testing should only be necessary during protocol discovery.

---

# Notes

This document intentionally separates **observations** from **verified protocol behavior**.

Keeping this distinction clear will prevent undocumented assumptions from becoming part of the implementation.
