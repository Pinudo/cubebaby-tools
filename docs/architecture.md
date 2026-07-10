# CubeBaby Tools Architecture

**Version:** 0.1

---

# Overview

CubeBaby Tools is designed around one fundamental principle:

> The communication protocol must be completely independent from the user interface.

This allows the same protocol implementation to be reused by:

- Android application
- Command Line Interface
- Desktop applications
- Future Web applications

The protocol should only exist once.

---

# High-Level Architecture

```
                    +-----------------------+
                    |   Android Application |
                    +-----------+-----------+
                                |
                    +-----------v-----------+
                    |     CubeBaby CLI      |
                    +-----------+-----------+
                                |
                    +-----------v-----------+
                    |    CubeBaby Core      |
                    +-----------+-----------+
                                |
                    +-----------v-----------+
                    |     MIDI Transport    |
                    +-----------+-----------+
                                |
                          USB MIDI Device
                                |
                          Cube Baby Pedal
```

---

# Project Modules

## cubebaby-core

Purpose

Contains all business logic.

Responsibilities

- Packet creation
- Packet parsing
- Encoder
- Decoder
- Checksum
- Protocol state
- Memory operations
- IR conversion
- Protocol exceptions

This module must have **no Android dependencies**.

---

## cubebaby-cli

Purpose

Simple command line interface.

Responsibilities

- Execute protocol commands
- Debug protocol
- Display raw packets
- Assist reverse engineering

The CLI contains no protocol implementation.

It only calls cubebaby-core.

---

## cubebaby-android

Purpose

Android application.

Responsibilities

- USB permissions
- USB Host API
- MIDI transport
- UI
- File picker
- IR selection

No protocol logic belongs here.

---

## docs

Purpose

Protocol documentation.

Everything discovered during reverse engineering must be documented before implementation.

---

## samples

Purpose

Sample captures.

Examples

- SysEx dumps
- Firmware responses
- Memory dumps
- Example IR files

---

# Dependency Graph

```
Android App

↓

CLI

↓

CubeBaby Core

↓

Transport Interface

↓

USB MIDI Implementation
```

Dependencies must never point upward.

---

# Core Packages

```
cubebaby-core

protocol/

packet/

transport/

memory/

encoder/

decoder/

checksum/

ir/

exceptions/

model/

util/
```

---

# Protocol Layer

Responsibilities

- Build packets
- Validate packets
- Encode
- Decode
- Verify checksum

No transport knowledge.

---

# Transport Layer

Responsibilities

- Send bytes
- Receive bytes

No protocol knowledge.

Possible implementations

- Android USB MIDI
- Desktop MIDI
- BLE MIDI (future)

---

# Memory Layer

Responsibilities

High-level API.

Example

```
readMemory()

writeMemory()

eraseMemory()
```

This layer hides packet construction.

---

# IR Layer

Responsibilities

- WAV parsing
- Sample validation
- Resampling (future)
- Float conversion
- Binary formatting

---

# Error Handling

Protocol errors should be explicit.

Examples

```
ChecksumException

PacketException

TransportException

UnsupportedDeviceException

TimeoutException
```

---

# Testing Strategy

Unit Tests

- Encoder
- Decoder
- Checksum
- Packet Builder

Integration Tests

- USB MIDI
- Device detection
- Packet exchange

Hardware Tests

- Real Cube Baby device

---

# Design Principles

## Documentation First

Every protocol discovery is documented before implementation.

---

## Single Responsibility

Every class has one responsibility.

---

## Platform Independence

Core never depends on Android.

---

## Android First

Android is the reference platform.

Desktop support is optional.

---

## Testability

Every protocol component must be unit-testable.

---

# Future Architecture

```
            CubeBaby Core

          /        |        \

 Android    Desktop    WebMIDI
```

The protocol implementation should never change.

Only the transport implementation changes.

---

# Initial Milestone

The first executable goal is:

```
Android

↓

USB Host

↓

USB MIDI

↓

Send SysEx

↓

Receive Response
```

No UI features will be implemented before this milestone.

---

# Long-Term Vision

CubeBaby Tools aims to become the reference open-source implementation of the Cube Baby communication protocol.

Every protocol feature should be reusable by third-party applications.
