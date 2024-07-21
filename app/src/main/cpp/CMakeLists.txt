cmake_minimum_required(VERSION 3.22.1)

project("AndroidMenu")

file(GLOB KittyMemory "Tools/KittyMemory/*.cpp" "Tools/KittyMemory/*.h")
file(GLOB SubstrateHook "Tools/SubstrateHook/*.cpp" "Tools/SubstrateHook/*.h" "Tools/SubstrateHook/*.hpp" "Tools/SubstrateHook/*.c")

add_library(${PROJECT_NAME} SHARED main.cpp ${KittyMemory} ${SubstrateHook})

find_library(log-lib log)

target_link_libraries(${PROJECT_NAME} ${log-lib})