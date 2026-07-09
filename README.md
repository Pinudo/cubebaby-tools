# CubeBaby Tools

> Open Source toolkit for M-VAVE Cube Baby pedals.
>
> **Android First. USB MIDI Native. No CubeSuite Required.**

---

## Overview

CubeBaby Tools is an open source project whose goal is to understand, document and implement the communication protocol used by the M-VAVE Cube Baby family.

Instead of depending on the official CubeSuite application, this project aims to provide a complete cross-platform toolkit capable of communicating directly with the pedal through USB MIDI.

Supported devices (planned):

- Cube Baby Guitar
- Cube Baby AC
- Cube Baby Bass

---

## Project Goals

- Reverse engineer the Cube Baby protocol
- Fully document the SysEx protocol
- Read and write pedal memory
- Backup and restore presets
- Upload and download IRs
- Android native support
- Cross-platform architecture
- Open documentation

---

## Project Status

Current stage:

> **Milestone 0 — Foundation**

Current objectives:

- [ ] Detect Cube Baby device
- [ ] Open USB MIDI interface
- [ ] Send SysEx packets
- [ ] Receive SysEx packets
- [ ] Read firmware information
- [ ] Read current preset

---

## Architecture

The project is divided into independent modules.

```
                +----------------------+
                |  CubeLoader Android  |
                +----------+-----------+
                           |
                +----------v-----------+
                |     CubeBaby CLI     |
                +----------+-----------+
                           |
                +----------v-----------+
                |    CubeBaby Core     |
                +----------+-----------+
                           |
                +----------v-----------+
                |     MIDI Transport   |
                +----------+-----------+
                           |
                      USB MIDI Device
                           |
                     M-VAVE Cube Baby
```

The core library contains **all protocol logic**.

Applications should never manipulate SysEx packets directly.

---

## Planned Modules

```
cubebaby-tools/

├── docs/
│
├── cubebaby-core/
│
├── cubebaby-cli/
│
├── cubeloader-android/
│
└── samples/
```

---

## Technology Stack

Language

- Kotlin

Platforms

- Android
- JVM
- Linux (future)
- Windows (future)

Communication

- USB Host API
- USB MIDI
- MIDI SysEx

---

## Reverse Engineering

Current discoveries include:

- USB MIDI transport
- SysEx communication
- 7-bit payload encoding
- Packet checksum
- Memory read/write model
- IR format

Remaining work:

- Complete memory map
- Preset structure
- IR upload protocol

---

## Roadmap

### Milestone 0

Foundation

- USB detection
- MIDI transport
- Packet encoder
- Packet decoder

### Milestone 1

Communication

- Device discovery
- Firmware query
- Memory read

### Milestone 2

Memory

- Read preset
- Write preset
- Backup
- Restore

### Milestone 3

Impulse Responses

- Upload IR
- Download IR
- IR conversion

### Milestone 4

Android Application

- Native editor
- Tone Vault
- Preset management

---

## Philosophy

CubeBaby Tools follows a simple principle:

> Every discovered protocol detail must be documented before implementation.

Documentation is considered a first-class deliverable.

---

## License

This project will be released under the MIT License.

---

## Contributing

Contributions are welcome.

Before implementing new features, please document any protocol discovery under the `/docs` directory.

---

## Disclaimer

CubeBaby Tools is an independent community project.

This project is not affiliated with or endorsed by M-VAVE or Cuvave.
