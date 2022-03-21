# Run

`docker-compose up`

# API

## GET rooms

Example:
`http://localhost:80/api/v1/rooms?start=2023-03-20%2010:10&end=2023-03-20%2010:50&attendees=1&multimedia=true&building_id=2`

`building_id` is optional

## GET Reservation

Example:
`http://localhost:80/api/v1/reservations?date=2022-03-21`

## POST Reservation

Example:
`http://localhost:80/api/v1/reserve`

Request Body example:

```
{
    "room_id": 1,
    "start": "2023-04-20T19:00:00",
    "end": "2023-04-20T20:00:00"
}
```