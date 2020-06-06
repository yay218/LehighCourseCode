  global main
  extern printf
  segment .bss
  i resq 1
  j resq 1
  section .data
fmt: db '%ld ', 0
endl: db 10, 0
  section .text
main:
  mov rax,5
  push rax
  pop qword[i]
  push qword[i]
  mov rax,7
  push rax
  pop rbx
  pop rax
  imul rbx
  push rax
  pop qword[j]
  push qword[i]
  pop rax
  push rbp
  mov rdi,fmt
  mov rsi,rax
  mov rax,0
  call printf
  pop rbp
  mov rax,0
  push qword[j]
  pop rax
  push rbp
  mov rdi,fmt
  mov rsi,rax
  mov rax,0
  call printf
  pop rbp
  mov rax,0
  push rbp
  mov rdi,endl
  mov rax,0
  call printf
  pop rbp
  mov rax,0
  ret
