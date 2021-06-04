cmake_minimum_required(VERSION 2.6)

project(cecho)

message(STATUS "Host is: ${CMAKE_HOST_SYSTEM}.  Build target is: ${CMAKE_SYSTEM}")

set(SRC
  main.c
)

message(STATUS "OS type: ${CMAKE_SYSTEM_NAME}")

add_executable(cecho ${SRC})
add_definitions(-DNDEBUG)
set_property(TARGET cecho PROPERTY C_STANDARD 99)
