	;; CSE109
	;; Yang Yi
	;; yay218
	;; Hw3
	
	SYS_EXIT equ 1
	SYS_READ equ 3
	SYS_WRITE equ 4
	STDIN equ 0
	STDOUT equ 1

	section .data 		;data segment
str:	db 'This is a    test' , 0x0
	lenStr	equ $ - str
	line	db 0xa
	len equ $ - line

	section .text		;code segment
	global main
main:
	mov ecx, str
	mov edx, 0		;set length

	call token


token:

lab1:
	cmp [ecx], byte ' '	;check space
	je lab2
	cmp [ecx], byte 0	;chexk 0 byte
	je lab3
	inc edx
	inc ecx
	jmp lab1

lab2:
	mov edi, ecx
	sub ecx, edx
	mov eax, SYS_WRITE
	mov ebx, STDOUT
	int 0x80

	jmp newLine
	mov edx, len
	mov ecx, line

	mov eax, SYS_WRITE
	mov ebx, STDOUT
	int 80h

	mov ecx, edi
	inc ecx
	mov edx, 0
	jmp lab1

lab3:
	sub ecx, edx
	mov eax, SYS_WRITE
	mov ebx, STDOUT
	int 0x80
	mov edx, len
	mov ecx, line

	mov eax, SYS_WRITE
	mov ebx, STDOUT
	int 0x80

	mov eax, SYS_EXIT
	int 0x80

lab4:
	inc edi
	mov ecx, edi
	jmp lab1

newLine:
	cmp [edi-1], byte ' '
	je lab4

	mov edx, len
	mov ecx, line

	mov eax, SYS_WRITE
	mov ebx, STDOUT
	int 80h

	mov ecx, edi
	inc ecx
	mov edx, 0
	jmp lab1
	