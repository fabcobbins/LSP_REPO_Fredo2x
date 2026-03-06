# CRC Cards — Air Traffic Control (ATC) System
## Assignment 4 | org.howard.edu.lsp.assignment4.doc

---

## Design Overview

The ATC system is decomposed into 9 classes covering:
- **Data ingestion**: TransponderPacket, GroundStation, PacketDecoder
- **Storage**: Aircraft, AircraftDatabase
- **Display**: RadarDisplay
- **Danger detection**: DangerAnalyzer, Alert
- **Controller query**: QueryHandler

---

## CRC Cards

---

### CRC Card

**Class:** TransponderPacket

**Responsibilities:**
- Hold raw broadcast data received from an aircraft transponder
- Store aircraft type identifier and encoded flight data fields
- Provide read access to raw packet fields for decoding

**Collaborators (if any):**
- GroundStation
- PacketDecoder

**Assumptions (if any):**
- Each packet contains at minimum: aircraft ID, aircraft type, position (lat/lon), altitude, speed, and heading
- Packet format is fixed-width, high-density binary/encoded format

---

### CRC Card

**Class:** GroundStation

**Responsibilities:**
- Listen for incoming transponder broadcasts from aircraft in range
- Construct a TransponderPacket from each received broadcast signal
- Forward each TransponderPacket to PacketDecoder for processing

**Collaborators (if any):**
- TransponderPacket
- PacketDecoder

**Assumptions (if any):**
- GroundStation operates continuously and handles one broadcast at a time
- Signal reception reliability and hardware concerns are out of scope

---

### CRC Card

**Class:** PacketDecoder

**Responsibilities:**
- Unpack high-density encoded fields from a TransponderPacket
- Extract structured flight data (aircraft type, position, altitude, speed, heading)
- Construct or update an Aircraft record with the decoded values
- Pass the updated Aircraft record to AircraftDatabase for storage

**Collaborators (if any):**
- TransponderPacket
- Aircraft
- AircraftDatabase

**Assumptions (if any):**
- Decoder knows the schema/format of the high-density packet (e.g., bit offsets, encoding)
- A new Aircraft is created on first sighting; subsequent packets update the existing record

---

### CRC Card

**Class:** Aircraft

**Responsibilities:**
- Store aircraft identity (flight ID, aircraft type)
- Store current flight data (latitude, longitude, altitude, speed, heading)
- Record the timestamp of the most recent data update
- Provide read access to all stored flight attributes

**Collaborators (if any):**
- (none — domain data object)

**Assumptions (if any):**
- Aircraft is a plain data-holding class with no business logic
- Flight ID uniquely identifies each aircraft in the system

---

### CRC Card

**Class:** AircraftDatabase

**Responsibilities:**
- Store Aircraft records indexed by flight ID
- Insert a new Aircraft record when a previously unseen aircraft is detected
- Update an existing Aircraft record with fresh flight data
- Retrieve a single Aircraft record by flight ID
- Return the full collection of currently tracked aircraft

**Collaborators (if any):**
- Aircraft

**Assumptions (if any):**
- Storage is in-memory for this design; persistence layer is out of scope
- An aircraft record is removed after a configurable inactivity timeout (stale data cleanup)

---

### CRC Card

**Class:** RadarDisplay

**Responsibilities:**
- Query AircraftDatabase every 10 seconds to retrieve all tracked aircraft
- Build a graphical representation of aircraft positions on the radar screen
- Render each aircraft's icon, flight ID, altitude, and heading on the display
- Refresh the full display on each update cycle

**Collaborators (if any):**
- AircraftDatabase
- Aircraft

**Assumptions (if any):**
- The 10-second refresh cycle is driven by an internal timer within RadarDisplay
- Rendering output targets a 2D map/scope view standard in ATC systems

---

### CRC Card

**Class:** DangerAnalyzer

**Responsibilities:**
- Retrieve all current Aircraft records from AircraftDatabase on each analysis cycle
- Compute projected trajectories for each tracked aircraft
- Detect proximity violations (two aircraft within minimum safe separation distance)
- Detect altitude conflicts between aircraft on converging paths
- Generate an Alert for each detected dangerous situation

**Collaborators (if any):**
- AircraftDatabase
- Aircraft
- Alert

**Assumptions (if any):**
- Analysis runs on the same 10-second cycle as RadarDisplay
- Minimum safe separation thresholds (horizontal and vertical) are defined as system constants
- DangerAnalyzer does not take corrective action — it only detects and reports

---

### CRC Card

**Class:** Alert

**Responsibilities:**
- Store the type of dangerous situation detected (e.g., proximity conflict, altitude conflict)
- Store references to the aircraft involved in the dangerous situation
- Store the severity level of the alert (warning vs. critical)
- Provide read access to alert details for display or logging

**Collaborators (if any):**
- Aircraft

**Assumptions (if any):**
- Alerts are immutable once created; a new Alert is generated each analysis cycle if the conflict persists
- Alert display/notification to the controller is handled by RadarDisplay, not Alert itself

---

### CRC Card

**Class:** QueryHandler

**Responsibilities:**
- Accept a flight ID query submitted by the ATC controller
- Retrieve the matching Aircraft record from AircraftDatabase
- Return full aircraft details (type, position, altitude, speed, heading, last update time) to the controller
- Return a not-found response when the queried flight ID does not exist in the database

**Collaborators (if any):**
- AircraftDatabase
- Aircraft

**Assumptions (if any):**
- Queries are interactive and on-demand — the controller initiates each query manually
- QueryHandler is read-only; it does not modify any Aircraft or database state

---

## Class Summary Table

| Class             | Primary Role                              |
|-------------------|-------------------------------------------|
| TransponderPacket | Raw broadcast data container              |
| GroundStation     | Receives aircraft transponder signals     |
| PacketDecoder     | Decodes packets into structured records   |
| Aircraft          | Domain data object for one aircraft       |
| AircraftDatabase  | Stores and retrieves all aircraft records |
| RadarDisplay      | Builds and refreshes the ATC radar screen |
| DangerAnalyzer    | Detects proximity and altitude conflicts  |
| Alert             | Represents a detected dangerous situation |
| QueryHandler      | Handles controller queries for flight info|
